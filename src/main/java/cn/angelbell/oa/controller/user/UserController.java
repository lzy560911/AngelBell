package cn.angelbell.oa.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.angelbell.oa.controller.BaseController;
import cn.angelbell.oa.entity.User;
import cn.angelbell.oa.service.user.UserService;
import cn.angelbell.oa.util.AppUtil;
import cn.angelbell.oa.util.PageData;

/**
 * 用户控制器.
 * 1.获取全部用户信息
 * @author liziye
 * @version 2019-07-16
 */

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取全部用户信息.
	 */
	@RequestMapping(value="/list")  
    public ModelAndView userLogin() throws Exception{
		logBefore(logger, "获取全部用户信息");
		List<User> list = new ArrayList<User>();
		list = userService.getUsersTypeList();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/userList");
		mv.addObject("user", list);
		return mv;
    }
	
	/**
	 * 跳转添加用户页面.
	 */
	@RequestMapping(value="/add")  
    public ModelAndView addUser() throws Exception{
		logBefore(logger, "添加用户");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/addUser");
		return mv;
    }
	
	/**处理登录
	 * @throws Exception
	 */
	@RequestMapping(value="/doAdd" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object doAdd() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		logBefore(logger, "添加用户信息");
		
		String errInfo = "";
		String msg = "";
		
		Date date=new Date();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		User user = new User();
		user.setUsername(pd.getString("username"));
		user.setRealname(pd.getString("realname"));
		user.setPassword(pd.getString("pass"));
		user.setSalt(pd.getString("username"));
		user.setAvatar("D://images");
		user.setPhone(pd.getString("tel"));
		user.setEmail(pd.getString("email"));
		user.setLocked((byte) 0);
		user.setSex((byte) pd.getInt("sex"));
		user.setCtime(date);
		try {
			userService.saveUser(user);
			errInfo = "01";
			msg = "添加成功";
		} catch (Exception e) {
			// TODO: handle exception
			errInfo = "00";
			msg = "添加失败";
			e.printStackTrace();
		}
		map.put("result", errInfo);
		map.put("msg", msg);
		return AppUtil.returnObject(new PageData(), map);
	}
}
