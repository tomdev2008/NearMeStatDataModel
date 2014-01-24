package com.nearme.statistics.service.app.zjb;

import java.util.List;

import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.model.zjb.BasedateEntity;
import com.nearme.statistics.model.zjb.ModuleinstallEntity;
import com.nearme.statistics.model.zjb.PerformanceEntity;
import com.nearme.statistics.model.zjb.ServermodelEntity;
import com.nearme.statistics.model.zjb.SoftinstallEntity;
import com.nearme.statistics.model.zjb.TuiguanginstallEntity;
import com.nearme.statistics.model.zjb.ZJBActivedaysdistributeEntity;
import com.nearme.statistics.model.zjb.ZJBOnlinepkginnerinstallEntity;
import com.nearme.statistics.model.zjb.ZJBOnlinepkginstallEntity;
import com.nearme.statistics.model.zjb.ZJBPkginstallEntity;
import com.nearme.statistics.model.zjb.ZJBRunpointEntity;
import com.nearme.statistics.model.zjb.ZJBSoftdownEntity;
import com.nearme.statistics.model.zjb.ZJBSoftdowntopEntity;
import com.nearme.statistics.model.zjb.ZJBUseractionEntity;
import com.nearme.statistics.model.zjb.ZJBVersionanalyEntity;
import com.nearme.statistics.model.zjb.ZjbVersionEntity;
import com.nearme.statistics.model.zjb.ChannelserviceEntity;

public interface ZjbService {
	// 版本
	public List<ZjbVersionEntity> getZjbVersionList(ZjbDTO dto);

	// 基础数据
	public List<BasedateEntity> getBasedateList(ZjbDTO dto);

	// 软件安装排行
	public List<SoftinstallEntity> getSoftinstallList(ZjbDTO dto);

	// 服务机型
	public List<ServermodelEntity> getServermodelList(ZjbDTO dto);
	public List<ServermodelEntity> getServermodelList2(ZjbDTO dto);

	// 模块安装量
	public List<ModuleinstallEntity> getModuleinstallList(ZjbDTO dto);

	// 推广软件安装
	public List<TuiguanginstallEntity> getTuiguanginstallList(ZjbDTO dto);

	// 性能指标
	public List<PerformanceEntity> getPerformanceList(ZjbDTO dto);
	
	
	
	/**
	 * 月活天数分布
	 * @param dto
	 * @return
	 */
	public List<ZJBActivedaysdistributeEntity> getActivedaysdistributeList(ZjbDTO dto);
	
	/**
	 * 用户行为分析
	 * @param dto
	 * @return
	 */
	public List<ZJBUseractionEntity> getUseractionList(ZjbDTO dto);
	
	/**
	 * 软件下载
	 * @param dto
	 * @return
	 */
	public List<ZJBSoftdownEntity> getSoftdownList(ZjbDTO dto);
	
	/**
	 * 软件下载top
	 * @param dto
	 * @return
	 */
	public List<ZJBSoftdowntopEntity> getSoftdowntopList(ZjbDTO dto);
	
	/**
	 * 资源包安装
	 * @param dto
	 * @return
	 */
	public List<ZJBPkginstallEntity> getPkginstallList(ZjbDTO dto);
	
	/**
	 * 单个资源运营点
	 * @param dto
	 * @return
	 */
	public List<ZJBRunpointEntity> getRunpointList(ZjbDTO dto);//默认
	public List<ZJBRunpointEntity> getRunpointAddList(ZjbDTO dto);//附加
	public List<ZJBRunpointEntity> getRunpointSearchList(ZjbDTO dto);//搜索
	public void deleteRunpointsoft(ZjbDTO dto);//删除添加项
	public void addRunpointsoft(ZjbDTO dto);//添加搜索项
	
	/**
	 * 在线资源包软件安装
	 * @param dto
	 * @return
	 */
	public List<ZJBOnlinepkginstallEntity> getOnlinepkginstallList(ZjbDTO dto);
	
	/**
	 * 在线资源包内软件安装
	 * @param dto
	 * @return
	 */
	public List<ZJBOnlinepkginnerinstallEntity> getOnlinepkginnerinstallList(ZjbDTO dto);
	
	/**
	 * 版本分析
	 * @param dto
	 * @return
	 */
	public List<ZJBVersionanalyEntity> getVersionanalyList(ZjbDTO dto);

	// 分渠道服务
	public List<ChannelserviceEntity> getChannelserviceList(ZjbDTO dto);
}
