package cn.angelbell.oa.service;

import java.util.List;
import org.springframework.stereotype.Service;

import cn.angelbell.oa.entity.Teacher;

/**
 * 
 * @ClassName: TeacherService
 * @Description: 教师业务逻辑接口
 * @author liziye
 * @date 2019.07.15
 */
@Service
public interface TeacherService {
	/**
     * 
     * @Title: getTeacherTypeList
     * @Description: 从数据库中获取全部教师信息
     * @throws Exception
     */
    List<Teacher> getTeacherTypeList() throws Exception;
    
    /**
     * 
     * @Title: selectByUserName
     * @Description: 根据教师真实姓名进行查询指定信息
     * @param realName 教师真实姓名
     * @throws Exception
     */
    Teacher selectByUserName(String realName) throws Exception;
}
