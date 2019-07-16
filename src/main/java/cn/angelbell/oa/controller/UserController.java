package cn.angelbell.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.angelbell.oa.entity.User;
import cn.angelbell.oa.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list")  
    public ModelAndView userLogin() throws Exception{
		List<User> list = new ArrayList<User>();
		list = userService.getUsersTypeList();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userList");
		mv.addObject("user", list);
		return mv;
    }
}
