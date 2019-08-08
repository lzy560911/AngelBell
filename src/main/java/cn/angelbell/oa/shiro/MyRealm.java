package cn.angelbell.oa.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import cn.angelbell.oa.entity.User;
import cn.angelbell.oa.service.user.UserService;

/**
 * 
 * @ClassName: MyRealm
 * @Description: 自定义realm
 * @author cheng
 * @date 2017年10月9日 上午10:54:00
 */
public class MyRealm extends AuthorizingRealm {
	
	@Resource
    private UserService userService;

    /**
     * 用于认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("使用了自定义的realm,用户认证...");
        System.out.println("用户名:" + ((UsernamePasswordToken) token).getUsername());
        System.out.println("密码:" + new String(((UsernamePasswordToken) token).getPassword()));

        // 获取用户名
        String userName = (String) token.getPrincipal();
        User userInfo = null;
        try {
            userInfo = userService.selectByUserName(userName);
           
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(userInfo != null){
			String password = userInfo.getPassword();
	        ByteSource salt = ByteSource.Util.bytes(userInfo.getSalt());
	        System.out.println(password+":"+salt);
	        // 与UsernamePasswordToken(userName, password)进行比较
	        // 区别UsernamePasswordToken(userName, password)中的password是用户输入的密码，即没有加密过的密码
	        // SimpleAuthenticationInfo(userName, password, ByteSource.Util.bytes(salt), this.getName())中的password是数据库中的密码，即加密过后的密码
	        return new SimpleAuthenticationInfo(userName, password, salt, this.getName());
		}
		return null;
    }

    /**
     * 用于授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("使用了自定义的realm,用户授权...");

        // 获取用户名
        // String userName = (String) principals.getPrimaryPrincipal();
        // 依据用户名在数据库中查找权限信息

        // 角色
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        roles.add("user");
        // 权限
        List<String> permissions = new ArrayList<>();
        permissions.add("admin:select");
        permissions.add("admin:delete");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        simpleAuthorizationInfo.addRoles(roles);
        return simpleAuthorizationInfo;
    }

}