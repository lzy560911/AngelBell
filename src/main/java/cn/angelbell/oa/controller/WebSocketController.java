package cn.angelbell.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/webSocket")
public class WebSocketController {
	@RequestMapping(value="/webSocket1")  
    public String webSocket1() {
		System.out.println("this is the websocket1");
        return "webSocket";  
          
    }
	
	@RequestMapping(value="/webSocket2")  
    public ModelAndView webSocket2() {
		System.out.println("this is the websocket2");
		ModelAndView mv =  new ModelAndView("webSocket");
		System.out.println("this is the websocket222222");
        return mv;
    }
	
	@RequestMapping(value="/webSocket3")  
    public ModelAndView webSocket3() {
		System.out.println("this is the websocket3");
		ModelAndView mv =  new ModelAndView("hello");
		System.out.println("this is the websocket33333");
        return mv;
    }
	
	@RequestMapping(value="/webSocket4")  
    public String webSocket4() {
		System.out.println("this is the websocket4");
        return "hello";  
          
    }
}
