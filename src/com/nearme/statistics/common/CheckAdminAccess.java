package com.nearme.statistics.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.admin.IpFilter;
import com.nearme.statistics.service.sys.AdminService;
import com.nearme.statistics.util.IpUtil;
import com.nearme.statistics.util.StringUtil;
/**
 * 检查管理员访问功能权限
 * @author 80053813 罗勇
 * @version 1.0
 * @since 1.0 2012-6-11
 */
public class CheckAdminAccess {

//	private static Log log = LogFactory.getLog("VISITOR");
	private static Logger log = Logger.getLogger(CheckAdminAccess.class);
	/**
	 * 检查进入权限（登录之后使用）
	 * @param func 功能编码
	 * 		特别注意需要加上菜单编码前面的'M'
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean checkAccessable(String func, HttpSession session) {
		if(session == null){
			return false;
		}
		Map<String,String> perms = (Map<String, String>) session.getAttribute(Constants.SESSION_PERMS_KEY);
		Admin admin = (Admin)session.getAttribute(Constants.SESSION_ADMIN_KEY);
		if(perms == null || admin == null){
			return false;
		}
		if(perms.containsKey("all")){
			log.info(String.format("AdminID=%d,\tAdminName=%s,\tFunc=%s,\tAccessed", 
					admin.getId(), admin.getUserName(), func));
			return true;
		}
		if(perms.containsKey(func)){
			log.info(String.format("AdminID=%d,\tAdminName=%s,\tFunc=%s,\tAccessed", 
					admin.getId(), admin.getUserName(), func));
			return true;
		}else{
			log.info(String.format("AdminID=%d,\tAdminName=%s,\tFunc=%s,\tDenied", 
					admin.getId(), admin.getUserName(), func));
			return false;
		}
	}
	/**
	 * 检查IP登录限制
	 * @param service
	 * @param admin
	 * @param loginIP
	 * @return
	 */
	public static boolean checkIPAccessable(AdminService service, Admin admin,String loginIP){
		if(admin == null){
			return false;
		}
		if("admin".equalsIgnoreCase(admin.getUserName())){
			//超级管理员,无敌存在
			return true;
		}
		if(admin.getIpLimit() == 2){
			//无限制
			return true;
		}
		
		long ipNumber = IpUtil.getIPNumber(loginIP);
		if(ipNumber == 0){
			//有可能是未知ＩＰ
			return false;
		}
		//只允许白名单
		if(admin.getIpLimit() == 0){
			List<IpFilter> list = service.getWhiteFilterList();
//			if(list == null){
//				IPFilterDao dao = new IPFilterDao();
//				list = dao.getWhiteList(null);
//				CachedItems.setCache("IP_WHITE_LIST", list);
//			}
			if(list == null || list.size() == 0){
				return false;//没你能访问的IP,杯具！
			}
			for(IpFilter filter : list){
				if(filter.getStrIpRegex() == null){
					//限定IP段方式
					if(ipNumber >= filter.getNumIpStart() && ipNumber <= filter.getNumIpEnd()){
						return true;
					}
				}else if(StringUtil.isMatch(loginIP, filter.getStrIpRegex())){
					return true;
				}
			}
		}
		//只禁止黑名单
		else if(admin.getIpLimit() == 1){
			List<IpFilter> list = service.getBlackFilterList();
//			if(list == null){
//				IPFilterDao dao = new IPFilterDao();
//				list = dao.getBlackList(null);
//				CachedItems.setCache("IP_BLACK_LIST", list);
//			}
			if(list == null || list.size() == 0){
				return true;//无限制
			}
			for(IpFilter filter : list){
				if(filter.getStrIpRegex() == null){
					//限定IP段方式
					if(ipNumber >= filter.getNumIpStart() && ipNumber <= filter.getNumIpEnd()){
						return false;
					}
				}else if(StringUtil.isMatch(loginIP, filter.getStrIpRegex())){
					return false;
				}
			}
		}
		
		return false;
	}
}
