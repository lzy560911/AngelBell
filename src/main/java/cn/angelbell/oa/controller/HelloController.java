package cn.angelbell.oa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping(value = "lzy")
	public String lzy() {
		return "liziye";
	}

}
