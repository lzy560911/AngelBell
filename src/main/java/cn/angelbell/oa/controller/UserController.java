package cn.angelbell.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	
	@RequestMapping(value="/userLogin")  
    public String userLogin() throws Exception{
		logBefore(logger, "跳转到登录页面....");
		return "login";
    }
}
