package cn.angelbell.oa.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @ClassName: ShiroAction
 * @Description: shiro控制层
 * @author cheng
 * @date 2017年10月10日 上午10:18:21
 */
@RestController
@RequestMapping(value = "/shiro")
public class ShiroAction {

    /**
     * 
     * @Title: userLogin
     * @Description: 用户登录
     * @return
     */
    @RequestMapping(value = "/userLogin")
    public String userLogin(String username, String password) {

        // 以下部分在配置阶段就已经完成,可以直接使用
        // 读取配置文件
        // Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 获取SecurityManager的实例
        // SecurityManager securityManager = factory.getInstance();
        // 把 securityManager 的实例绑定到 SecurityUtils 上
        // SecurityUtils.setSecurityManager(securityManager);

        System.out.println(username + ":" + password);
        String passwd1 = new SimpleHash("SHA-1", username,password).toString();	//密码加密
        String passwd2 = new SimpleHash("MD5", username,password).toString();	//密码加密
        System.out.println(passwd1+"::"+passwd2);
        Subject subject = SecurityUtils.getSubject();
        // 自己创建一个令牌，输入用户名和密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        System.out.println("usernamePasswordToken is ---->"+usernamePasswordToken);
        try {
            subject.login(usernamePasswordToken);
            System.out.println("身份认证成功！");

        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("账号不存在！");

        } catch (LockedAccountException e) {
            e.printStackTrace();
            System.out.println("账号被锁定！");

        } catch (DisabledAccountException e) {
            e.printStackTrace();
            System.out.println("账号被禁用！");

        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("凭证/密码错误！");

        } catch (ExpiredCredentialsException e) {
            e.printStackTrace();
            System.out.println("凭证/密码过期！");

        } catch (ExcessiveAttemptsException e) {
            e.printStackTrace();
            System.out.println("登录失败次数过多！");

        }

        // 是否认证通过
        boolean isAuthenticated1 = subject.isAuthenticated();
        System.out.println("登录后,是否认证通过：" + isAuthenticated1);

        // 退出
        subject.logout();

        // 是否认证通过
        boolean isAuthenticated2 = subject.isAuthenticated();
        System.out.println("退出登录后,是否认证通过：" + isAuthenticated2);

        return "处理登录";
    }

}
