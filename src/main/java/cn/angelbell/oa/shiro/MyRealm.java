package cn.angelbell.oa.shiro;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;

import cn.angelbell.oa.entity.User;
import cn.angelbell.oa.mapper.UserMapper;

/**
 * 
 * @ClassName: MyRealm
 * @Description: 自定义realm
 * @author cheng
 * @date 2017年10月9日 上午10:54:00
 */
public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserMapper userMapper;
    /**
     * 用于认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
    	System.out.println("进入自定义Realm.....");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = String.valueOf(token.getUsername());
        // 从数据库获取对应用户名的用户
        User user = null;
		try {
			System.out.println("自定义Realm准备查找用户信息.....");
			user = userMapper.selectByUserName(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (user == null) {
            throw new AccountException("不存在此用户");
        }
        // 获取盐值，即用户名
        ByteSource salt = ByteSource.Util.bytes(username);
        System.out.println("salt is -->"+salt);
        //注意，数据库中的user的密码必须是要经过md5加密，不然还是会抛出异常！！！！！
        System.out.println("自定义Realm结束.....");
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), salt, getName());
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