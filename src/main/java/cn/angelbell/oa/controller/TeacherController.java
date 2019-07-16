package cn.angelbell.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.angelbell.oa.entity.Teacher;
import cn.angelbell.oa.service.TeacherService;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController extends BaseController{
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/list")
	public ModelAndView teacherList() throws Exception{
		logBefore(logger, "获取全部教师信息");
		List<Teacher> list = new ArrayList<Teacher>();
		list = teacherService.getTeacherTypeList();
		for(int i = 0;i<list.size();i++){
			System.out.println("teacher name is :"+list.get(i).getRealname());
		}
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("teacherList");
		mv.addObject("teacher", list);
		return mv;
	}
}
