package cn.angelbell.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@RequestMapping(value="/userLogin")  
    public String webSocket1() {
		System.out.println("this is the websocket1");
        return "login";
    }
}
