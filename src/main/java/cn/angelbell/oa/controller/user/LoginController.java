package cn.angelbell.oa.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.angelbell.oa.controller.BaseController;
import cn.angelbell.oa.entity.User;
import cn.angelbell.oa.service.user.UserService;
import cn.angelbell.oa.util.AppUtil;
import cn.angelbell.oa.util.Const;
import cn.angelbell.oa.util.Jurisdiction;
import cn.angelbell.oa.util.PageData;
import cn.angelbell.oa.util.Tools;


@Controller
@RequestMapping(value = "/login")
public class LoginController  extends BaseController {
	
	@Resource
    private UserService userService;

	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/toLogin")
	public ModelAndView login() throws Exception{
		logBefore(logger, "跳转到登录页面");
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	/**处理登录
	 * @throws Exception
	 */
	@RequestMapping(value="/doLogin" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object doLogin() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		logBefore(logger, "登录验证");
		
		String errInfo = "";
		String msg = "";
		
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String password = pd.getString("password");
		System.out.println(username+":"+password);
		Session session = Jurisdiction.getSession();
		if(username!=null&&password!=null){
			Subject subject = SecurityUtils.getSubject();
		    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		    token.setRememberMe(true);
		    try {
		        subject.login(token); 
		        errInfo = "01"; 				//登录成功
				msg = "success";
				User userInfo = userService.selectByUserName(username);
				session.setAttribute(Const.SESSION_USER, userInfo);
				session.setAttribute("adminid",userInfo.getUserId());
				session.setAttribute("username",userInfo.getUsername());
		    }catch (UnknownAccountException e) {
		    	errInfo = "02";
		    	msg = "该账号不存在!";
	            e.printStackTrace();
	        }catch (IncorrectCredentialsException e) {
	        	errInfo = "03";
		    	msg = "账号密码错误!";
	            e.printStackTrace();
	        }catch (ExcessiveAttemptsException e) {
	        	errInfo = "04";
		    	msg = "对不起，您登录次数过多！";
	            e.printStackTrace();
	        }
		}else{
			errInfo = "00";
	    	msg = "用户名密码不能为空!";
		}
		map.put("result", errInfo);
		map.put("msg", msg);
		map.put("url", "/login/application");
		return AppUtil.returnObject(new PageData(), map);
	}
	/**访问系统首页
	 * @param changeMenu：切换菜单参数
	 * @return
	 */
	@RequestMapping(value="/application")
	public ModelAndView application(){
		System.out.println("this is the application");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Session session = Jurisdiction.getSession();
			User user = (User)session.getAttribute(Const.SESSION_USER);//读取session中的用户信息(单独用户信息)
			System.out.println("user session is --->"+user);
			if (user != null) {
				mv.setViewName("main");
			}else {
				mv.setViewName("login");//session失效后跳转登录页面
			}
		} catch(Exception e){
			mv.setViewName("login");
			logger.error(e.getMessage(), e);
		}
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout() throws Exception{
		String USERNAME = Jurisdiction.getUsername();	//当前登录的用户名
		logBefore(logger, USERNAME+"退出系统");
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		this.removeSession(USERNAME);//请缓存
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		pd = this.getPageData();
		pd.put("msg", pd.getString("msg"));
	
		mv.setViewName("login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 清理session
	 */
	public void removeSession(String USERNAME){
		Session session = Jurisdiction.getSession();	//以下清除session缓存
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute("userid");
		session.removeAttribute("username");
	}
}
