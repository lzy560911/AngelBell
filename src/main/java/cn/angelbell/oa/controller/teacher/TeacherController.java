package cn.angelbell.oa.controller.teacher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.angelbell.oa.controller.BaseController;
import cn.angelbell.oa.entity.Teacher;
import cn.angelbell.oa.service.teacher.TeacherService;


/**
 * 教师控制器.
 * 1.获取全部教师信息
 * @author liziye
 * @version 2019-07-16
 */

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController extends BaseController{
	
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * 获取全部教师信息.
	 */
	@RequestMapping(value="/list")
	public ModelAndView teacherList() throws Exception{
		logBefore(logger, "获取全部教师信息");
		List<Teacher> list = new ArrayList<Teacher>();
		list = teacherService.getTeacherTypeList();
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("teacher/teacherList");
		mv.addObject("teacher", list);
		return mv;
	}
	
	/**
	 * 添加教师信息.
	 */
	@RequestMapping(value="/add")  
    public ModelAndView addTeacher() throws Exception{
		logBefore(logger, "添加用户");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("teacher/addTeacher");
		return mv;
    }
}
