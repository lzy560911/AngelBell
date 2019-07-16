package cn.angelbell.oa.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.angelbell.oa.entity.Teacher;
import cn.angelbell.oa.mapper.TeacherMapper;
import cn.angelbell.oa.service.TeacherService;

/**
 * 
 * @ClassName: TeacherServiceImpl
 * @Description: 教师类型业务逻辑处理
 * @author liziye
 * @date 2019.07.15
 */
@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public List<Teacher> getTeacherTypeList() throws Exception {
		// TODO Auto-generated method stub
		List<Teacher> list = new ArrayList<Teacher>();
		list = teacherMapper.getList();
		return list;
	}

	@Override
	public Teacher selectByUserName(String realName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
