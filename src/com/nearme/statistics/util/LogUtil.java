package com.nearme.statistics.util;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dao.sys.LogDao;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.LogDTO;
import com.nearme.statistics.dto.RequestjobDTO;
import com.nearme.statistics.dto.app.common.ModelDTO;
import com.nearme.statistics.dto.app.common.ModuleDTO;
import com.nearme.statistics.dto.app.common.VersionDTO;
import com.nearme.statistics.dto.app.common.ChannelinfoDTO;
import com.nearme.statistics.dto.app.common.ModulerunpointDTO;
import com.nearme.statistics.dto.app.common.UseractionDTO;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.dto.app.store.StoreDTO;
import com.nearme.statistics.dto.app.zjb.ZJBChannelinfoDTO;
import com.nearme.statistics.dto.app.zjb.ZJBUseractioncodeDTO;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.model.sys.admin.Admin;

/**
 * 记录用户行为
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-9
 */
public class LogUtil {
	private static LogDao dao;

	static {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "spring-dao.xml", "spring-datasource.xml" });

			dao = (LogDao) context.getBean("logDao");
		} catch (Exception e) {
		}
	}

	/**
	 * 记录用户行为
	 * 
	 * @param dto
	 */
	public static void log(LogDTO dto) {
		try {
			if (null != dao) {
				// 填充操作人
				Admin admin = (Admin) ServletActionContext.getRequest()
						.getSession().getAttribute(Constants.SESSION_ADMIN_KEY);
				dto.setUsername(admin.getUserName());

				dao.logInfo(dto);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 记录BaseDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static LogDTO log(BaseDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setActioncode(dto.getActioncode());
			ldto.setAppVersion(dto.getAppVersion());
			ldto.setChannel(dto.getQudao());
			ldto.setEndDate(dto.getEndDate());
			ldto.setGroupcode(dto.getGroupcode());
			ldto.setImeitype(dto.getType());
			ldto.setLidu(dto.getLidu());
			ldto.setModel(dto.getModel());
			ldto.setNetworkType(dto.getNetworkType());
			ldto.setStatDate(dto.getStartDate());
			ldto.setSystemID(dto.getSystemID());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
		return ldto;
	}

	/**
	 * 记录AppversionDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static LogDTO log(VersionDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setSystemID(dto.getSystemID());
			ldto.setCondition1(dto.getVersionName());
			ldto.setCondition2(dto.getHide());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
		return ldto;
	}

	/**
	 * 记录StoreDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static void log(StoreDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setActioncode(dto.getActioncode());
			ldto.setAppVersion(dto.getAppVersion());
			ldto.setChannel(dto.getQudao());
			ldto.setEndDate(dto.getEndDate());
			ldto.setGroupcode(dto.getGroupcode());
			ldto.setImeitype(dto.getType());
			ldto.setLidu(dto.getLidu());
			ldto.setModel(dto.getModel());
			ldto.setNetworkType(dto.getNetworkType());
			ldto.setStatDate(dto.getStartDate());
			ldto.setSystemID(dto.getSystemID());
			ldto.setVisiturl(visiturl);
			ldto.setCondition1(dto.getUserType());
		}

		log(ldto);
	}

	/**
	 * 记录UseractionDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static void log(UseractionDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setActioncode(dto.getActioncode());
			ldto.setGroupcode(dto.getGroupcode());
			ldto.setSystemID(dto.getSystemID());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
	}

	/**
	 * 记录LirenDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static void log(LirenDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setActioncode(dto.getActioncode());
			ldto.setAppVersion(dto.getAppVersion());
			ldto.setChannel(dto.getQudao());
			ldto.setEndDate(dto.getEndDate());
			ldto.setGroupcode(dto.getGroupcode());
			ldto.setImeitype(dto.getType());
			ldto.setLidu(dto.getLidu());
			ldto.setModel(dto.getModel());
			ldto.setNetworkType(dto.getNetworkType());
			ldto.setStatDate(dto.getStartDate());
			ldto.setSystemID(dto.getSystemID());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
	}

	/**
	 * 记录GameDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static void log(GameDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setActioncode(dto.getActioncode());
			ldto.setAppVersion(dto.getAppVersion());
			ldto.setChannel(dto.getQudao());
			ldto.setEndDate(dto.getEndDate());
			ldto.setGroupcode(dto.getGroupcode());
			ldto.setImeitype(dto.getType());
			ldto.setLidu(dto.getLidu());
			ldto.setModel(dto.getModel());
			ldto.setNetworkType(dto.getNetworkType());
			ldto.setStatDate(dto.getStartDate());
			ldto.setSystemID(dto.getSystemID());
			ldto.setCondition1(dto.getGamename());
			ldto.setCondition2(dto.getRanktype());
			ldto.setCondition3("" + dto.getPosition());
			ldto.setCondition4(dto.getProductid());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
	}

	/**
	 * 记录BaseDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static void log(ZjbDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setActioncode(dto.getActioncode());
			ldto.setAppVersion(dto.getAppVersion());
			ldto.setChannel(dto.getQudao());
			ldto.setEndDate(dto.getEndDate());
			ldto.setGroupcode(dto.getGroupcode());
			ldto.setImeitype(dto.getType());
			ldto.setLidu(dto.getLidu());
			ldto.setModel(dto.getModel());
			ldto.setNetworkType(dto.getNetworkType());
			ldto.setStatDate(dto.getStartDate());
			ldto.setSystemID(dto.getSystemID());
			ldto.setCondition1(dto.getSoftName());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
	}

	/**
	 * 记录ChannelinfoDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static void log(ChannelinfoDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
	}

	/**
	 * 记录ModuleDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static LogDTO log(ModuleDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
		return ldto;
	}

	/**
	 * 记录ModulerunpointDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static LogDTO log(ModulerunpointDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
		return ldto;
	}

	/**
	 * 记录ZJBChannelinfoDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static void log(ZJBChannelinfoDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setCondition1("" + dto.getChannelID());
			ldto.setCondition2(dto.getChannelName());
			ldto.setCondition3(dto.getChannelDesc());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
	}

	/**
	 * 记录ZJBUseractioncodeDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 *            访问的模块
	 */
	public static void log(ZJBUseractioncodeDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setCondition1("" + dto.getActionID());
			ldto.setCondition2(dto.getActioncode());
			ldto.setCondition3(dto.getActionname());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
	}

	/**
	 * 记录ModelDTO的log
	 * 
	 * @param dto
	 * @param visiturl
	 */
	public static void log(ModelDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setCondition2(dto.getTypename());
			ldto.setCondition3("" + dto.getIsoppo());
			ldto.setCondition4("" + dto.getIsused());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
	}

	/**
	 * 记录RequestjobDTO 的log
	 * @param dto
	 * @param visiturl
	 */
	public static void log(RequestjobDTO dto, String visiturl) {
		LogDTO ldto = new LogDTO();

		if (null != dto) {
			ldto.setCondition1(dto.getCondition());
			ldto.setCondition2(dto.getIndicat());
			ldto.setVisiturl(visiturl);
		}

		log(ldto);
	}
}
