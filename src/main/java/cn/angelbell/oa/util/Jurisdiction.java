package cn.angelbell.oa.util;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * 权限处理
 * @author:fh qq 313596790[青苔]
 * 修改日期：2015/11/19
*/
public class Jurisdiction {

	
	/**获取当前登录的用户名
	 * @return
	 */
	public static Integer getUseid(){
		Integer userid = (Integer)getSession().getAttribute("userid");
		return userid;
	}
	/**获取当前登录的用户名
	 * @return
	 */
	public static String getUsername(){
		return getSession().getAttribute(Const.SESSION_USERNAME).toString();
	}
	/**获取当前登录的用户名
	 * @return
	 */
	public static String getCityId(){
		return getSession().getAttribute(Const.SESSION_CITY_ID).toString();
	}
	
	
	
	/**获取当前按钮权限(增删改查)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getHC(){
		return (Map<String, String>)getSession().getAttribute(getUsername() + Const.SESSION_QX);
	}
	
	/**shiro管理的session
	 * @return
	 */
	public static Session getSession(){
		//Subject currentUser = SecurityUtils.getSubject();  
		return SecurityUtils.getSubject().getSession();
	}
}
