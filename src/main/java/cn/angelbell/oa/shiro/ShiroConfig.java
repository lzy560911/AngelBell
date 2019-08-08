package cn.angelbell.oa.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 
 * @ClassName: ShiroConfig
 * @Description: spring整合shiro配置
 * @author cheng
 * @date 2017年10月10日 上午9:46:43
 */
@Configuration
public class ShiroConfig {

    /**
     * 
     * @Title: createMyRealm
     * @Description: 自定义的realm
     * @return
     */
    @Bean
    public MyRealm createMyRealm() {
        // 加密相关
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列次数
        hashedCredentialsMatcher.setHashIterations(1);
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    /**
     * 
     * @Title: securityManager
     * @Description: 注入自定义的realm
     * @Description: 注意方法返回值SecurityManager为org.apache.shiro.mgt.SecurityManager
     *               ,不要导错包
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(createMyRealm());
        return securityManager;
    }

    /**
     * 
     * @Title: shirFilter
     * @Description: Shiro 的Web过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/userLogin");
        // 登录成功后要跳转的链接,建议不配置,shiro认证成功自动到上一个请求路径
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面,指定没有权限操作时跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // 过滤器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login/toLogin","anon");
        filterChainDefinitionMap.put("/login/doLogin","anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/main");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

}