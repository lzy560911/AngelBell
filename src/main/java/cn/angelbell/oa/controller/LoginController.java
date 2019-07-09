package cn.angelbell.oa.controller;

import java.util.HashMap;
import java.util.Map;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.angelbell.oa.controller.BaseController;
import cn.angelbell.oa.entity.User;
import cn.angelbell.oa.service.UserService;
import cn.angelbell.oa.util.AppUtil;
import cn.angelbell.oa.util.Const;
import cn.angelbell.oa.util.Jurisdiction;
import cn.angelbell.oa.util.PageData;
import cn.angelbell.oa.util.Tools;




@Controller
@RequestMapping(value = "/login")
public class LoginController  extends BaseController {
	@Autowired
	private UserService userService;

	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/toLogin")
	public ModelAndView login() throws Exception{
		
		logBefore(logger, "登录");
		ModelAndView mv = this.getModelAndView();
		return mv;
	}
	
	@RequestMapping(value="/head")
	public ModelAndView lodeHead() throws Exception{
		
		logBefore(logger, "加载头部");
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("jsp/head");
		return mv;
	}
	
	@RequestMapping(value="/welcome")
	public ModelAndView lodeWelcome() throws Exception{
		
		logBefore(logger, "加载头部");
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("jsp/welcome");
		return mv;
	}
	
	/**处理登录
	 * @throws Exception
	 */
	@RequestMapping(value="/doLogin" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object doLogin() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		logBefore(logger, "验证登录");
		
		String errInfo = "";
		String msg = "";
		
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String password = pd.getString("password");
		if(username!=null&&password!=null){
			String passwd = new SimpleHash("SHA-1", username,password).toString();	//密码加密
			User user = userService.selectByUserName(username);
			Session session = Jurisdiction.getSession();
			if(passwd.equals(user.getPassword())){
				session.setAttribute(Const.SESSION_USER, user);
				session.setAttribute("adminid", user.getUserId());	
				session.setAttribute("username", user.getUsername());
				//shiro加入身份验证
				Subject subject = SecurityUtils.getSubject(); 
			    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			    token.setRememberMe(true);
			    try { 
			        subject.login(token); 
			    } catch (AuthenticationException e) { 
			    	errInfo = "身份验证失败！";
			    }
			    
			    errInfo = "01"; 				//登录成功
				msg = "success";
			}else{
				errInfo = "00"; 				//用户名或密码有误
				msg = "用户名不存在密码错误";
				logBefore(logger, username+"登录系统密码错误");
			}
		}else{
			errInfo = "00"; 				//用户名或密码有误
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
			User user = (User)session.getAttribute(Const.SESSION_USER);						//读取session中的用户信息(单独用户信息)
			System.out.println("user session is --->"+user);
			if (user != null) {
				System.out.println("this is index");
				mv.setViewName("main");
			}else {
				System.out.println("this is login");
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
