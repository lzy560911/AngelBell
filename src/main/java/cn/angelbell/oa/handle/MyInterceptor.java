package cn.angelbell.oa.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: MyInterceptor
 * @Description: 自定义拦截器
 * @author ziye.li
 * @date 2019.06.25
 */
@Component//将该组件加入spring ioc容器
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理的方法之前执行，true让行，false不让行
     */
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("方法执行之前===============");
        return true;
    }

    /**
     * 在请求处理方法之后执行
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        System.out.println("方法执行之后===============");
    }

    /**
     * 在DispatcherServlet处理后执行--清理工作(因为是单利)
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        System.out.println("执行清理工作===============");
    }

}
