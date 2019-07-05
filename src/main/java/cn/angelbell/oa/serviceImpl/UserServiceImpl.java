package cn.angelbell.oa.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.angelbell.oa.entity.User;
import cn.angelbell.oa.mapper.UserMapper;
import cn.angelbell.oa.service.UserService;

/**
 * 
 * @ClassName: UserServiceImpl
 * @Description: 用户类型业务逻辑处理
 * @author liziye
 * @date 2019.07.04
 */
@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class UserServiceImpl implements UserService{
	
	// 数据访问
    @Autowired
    private UserMapper userDao;

	@Override
	public List<User> getUsersTypeList() throws Exception {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<>();
		list = userDao.getList();
		return list;
	}

	@Override
	public User selectByUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectByUserName(userName);
	}

}
