package com.nearme.statistics.action.app.commonsetting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.dto.app.common.ModuleDTO;
import com.nearme.statistics.form.app.common.ModuleForm;
import com.nearme.statistics.model.commonsetting.ModuleEntity;
import com.nearme.statistics.service.app.common.ModuleService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelReader;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 模块管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-19
 */
public class ModuleAction extends ActionSupport {
	private static final long serialVersionUID = 8118258883806860108L;

	private final String TAG = "模块管理";
	private ModuleForm form;
	
	//导入excel
	private File excel;// 要导入的文件
	private String excelContentType;// 上传文件类型属性
	private String excelFileName;// 上传文件名属性
	private String saveDir;//保存位置
	
	private List<ModuleEntity> groupList;//分组信息
	private List<ModuleEntity> detailList;//详细信息
	private ModuleService service;

	public String init() {
		// 检查action传递过来的systemID合法性r
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		ModuleDTO dto = new ModuleDTO();
		dto.setSystemID("" + systemID);
		groupList = service.getGroupList(dto);

		LogUtil.log(dto, TAG + "-分组查询");

		return Action.SUCCESS;
	}
	
	/**
	 * 同步数据到hive
	 * @return
	 */
	public String sync(){
		service.sync2Hive();
		return Action.SUCCESS;
	}
	
	/**
	 * 从excel导入数据
	 * @return
	 */
	public String loadExcelData(){
		String systemID = form.getSystemID();
		
		if (null != excel) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(excel);
				ExcelReader excelReader = new ExcelReader();
				Map<Integer, List<String>> map = excelReader.readExcelContentStrs(fis);
				for (int i = 1; i <= map.size(); i++) {
					ArrayList<String> rowstr = (ArrayList<String>) map.get(i);
					
					String groupname = rowstr.get(0);
					String sourcecode = rowstr.get(1);
					String sourcedesc = rowstr.get(2);
					String categoryid = rowstr.get(3);
					String downtype = rowstr.get(4);
					
					if (sourcecode != null && !"".equals(sourcecode)) {
						sourcecode = "" + (int)Double.parseDouble(sourcecode);
					}
					if (categoryid != null && !"".equals(categoryid)) {
						categoryid = "" + (int)Double.parseDouble(categoryid);
					}
					
					ModuleDTO dto = new ModuleDTO();
					dto.setSystemID(systemID);
					dto.setSourcecode(sourcecode);
					dto.setGroupname(groupname);
					dto.setSourcedesc(sourcedesc);
					dto.setCategoryid(categoryid);
					dto.setDowntype(downtype);
					
					service.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != fis) {
					try {
						fis.close();
						excel.deleteOnExit();//删除临时文件
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		init();
		
		return Action.SUCCESS;
	}
	
	/**
	 * 导出
	 */
	public String export(){
		String systemID = form.getSystemID();
		
		ModuleDTO dto = new ModuleDTO();
		dto.setSystemID(systemID);
		
		//查询要导出的模块数据
		List<ModuleEntity> modulelist = service.getModuleList(dto);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.writeLine(new String[] { "模块名称", "SOURCE_CODE", "描述", "分类ID",
					"下载类型" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (ModuleEntity entity : modulelist) {
				String groupname = entity.getGroupname() == null ? "" : entity.getGroupname();
				String sourcecode = entity.getSourcecode() == null ? "" : entity.getSourcecode();
				String sourcedesc = entity.getSourcedesc() == null ? "" : entity.getSourcedesc();
				String categoryid = entity.getCategoryid() == null ? "" : entity.getCategoryid();
				String downtype = entity.getDowntype() == null ? "" : entity.getDowntype();
				
				eu.writeLine(new String[] {
						groupname,
						sourcecode,
						sourcedesc,
						categoryid,
						downtype});
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}
	
	/**
	 * 查询分组
	 * @return
	 */
	public String queryGroup(){
		String systemID = form.getSystemID();
		
		ModuleDTO dto = new ModuleDTO();
		dto.setSystemID(systemID);
		
		groupList = service.getGroupList(dto);
		
		return "module_group";
	}
	
	/**
	 * 详情查询
	 * @return
	 */
	public String queryDetail(){
		String systemID = form.getSystemID();
		String groupname = form.getGroupname();
		
		ModuleDTO dto = new ModuleDTO();
		dto.setSystemID(systemID);
		dto.setGroupname(groupname);
		
		detailList = service.getDetailList(dto);
		LogUtil.log(dto, TAG + "-详情查询");
		
		return "module_detail";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {
		String systemID = form.getSystemID();
		String groupname = form.getGroupname();
		String sourcecodes = form.getSourcecode();
		String sourcedesc = form.getSourcedesc();
		String categoryid = form.getCategoryid();
		String downtype = form.getDowntype();
		
		ModuleDTO dto = new ModuleDTO();
		dto.setSystemID(systemID);
		dto.setGroupname(groupname);
		dto.setSourcedesc(sourcedesc);
		dto.setCategoryid(categoryid);
		dto.setDowntype(downtype);
		
		//支持多sourcecode插入
		String[] scodes = sourcecodes.split(",");
		for (String sourcecode : scodes) {
			if(null != sourcecode && !"".equals(sourcecode)){
				dto.setSourcecode(sourcecode.trim());
				service.add(dto);
			}
		}
		detailList = service.getDetailList(dto);

		LogUtil.log(dto, TAG + "-添加");

		return "module_detail";
	}
	
	/**
	 * 更新详情
	 * 
	 * @return
	 */
	public String updateDetail() {
		String id = form.getId();
		String groupname = form.getGroupname();
		String sourcecode = form.getSourcecode();
		String sourcedesc = form.getSourcedesc();
		String categoryid = form.getCategoryid();
		String downtype = form.getDowntype();
		String systemID = form.getSystemID();
		
		ModuleDTO dto = new ModuleDTO();
		dto.setId(id);
		dto.setGroupname(groupname);
		dto.setSourcecode(sourcecode);
		dto.setSourcedesc(sourcedesc);
		dto.setCategoryid(categoryid);
		dto.setDowntype(downtype);
		dto.setSystemID(systemID);
		
		service.updateDetail(dto);
		detailList = service.getDetailList(dto);

		LogUtil.log(dto, TAG + "-更新详情");

		return "module_detail";
	}
	
	/**
	 * 更新分组
	 * 
	 * @return
	 */
	public String updateGroup() {
		String groupname = form.getGroupname();
		String systemID = form.getSystemID();
		
		ModuleDTO dto = new ModuleDTO();
		dto.setGroupname(groupname);
		dto.setSystemID(systemID);
		
		service.updateGroup(dto);

		LogUtil.log(dto, TAG + "-更新分组");

		return Action.SUCCESS;
	}
	
	/**
	 * 删除详情(支持批量删)
	 * 
	 * @return
	 */
	public String deleteDetail() {
		String id = form.getId();
		String systemID = form.getSystemID();
		String groupname = form.getGroupname();
		
		ModuleDTO dto = new ModuleDTO();
		dto.setId(id);
		dto.setSystemID(systemID);
		dto.setGroupname(groupname);
		if (null != id && !"".equals(id)) {
			service.deleteDetail(dto);
		}
		detailList = service.getDetailList(dto);

		LogUtil.log(dto, TAG + "-删除详情");

		return "module_detail";
	}
	
	/**
	 * 删除分组(支持批量删)
	 * 
	 * @return
	 */
	public String deleteGroup() {
		String systemID = form.getSystemID();
		String groupname = form.getGroupname();
		
		ModuleDTO dto = new ModuleDTO();
		dto.setSystemID(systemID);
		dto.setGroupname(groupname);
		if (null != groupname && !"".equals(groupname)) {
			service.deleteGroup(dto);
		}

		LogUtil.log(dto, TAG + "-删除分组");

		return Action.SUCCESS;
	}

	public ModuleForm getForm() {
		return form;
	}

	public void setForm(ModuleForm form) {
		this.form = form;
	}

	public List<ModuleEntity> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<ModuleEntity> groupList) {
		this.groupList = groupList;
	}

	public List<ModuleEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ModuleEntity> detailList) {
		this.detailList = detailList;
	}

	public ModuleService getService() {
		return service;
	}

	public void setService(ModuleService service) {
		this.service = service;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public String getExcelContentType() {
		return excelContentType;
	}

	public void setExcelContentType(String excelContentType) {
		this.excelContentType = excelContentType;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
}
