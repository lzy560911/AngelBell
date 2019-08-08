package cn.angelbell.oa.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.angelbell.oa.entity.GoodsType;
import cn.angelbell.oa.entity.User;

/**
 * 
 * @ClassName: UserService
 * @Description: 用户业务逻辑接口
 * @author liziye
 * @date 2019.07.04
 */
@Service
public interface UserService {
	/**
     * 
     * @Title: getList
     * @Description: 从数据库中获取全部用户信息
     * @return
     * @throws Exception
     */
    List<User> getUsersTypeList() throws Exception;
    
    /**
     * 
     * @Title: selectByUserName
     * @Description: 根据用户账号进行查询
     * @param typeName
     * @return
     * @throws Exception
     */
    User selectByUserName(String userName) throws Exception;
    
    /**
     * 
     * @Title: saveUser
     * @Description: 添加用户信息
     * @param user
     * @return
     * @throws Exception
     */
    int saveUser(User user) throws Exception;
}
