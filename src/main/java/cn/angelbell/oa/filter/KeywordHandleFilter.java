package cn.angelbell.oa.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * desc : 关键词处理过滤器
 * create_user : liziye
 * create_date : 2019/7/2
 */
public class KeywordHandleFilter implements Filter {

    /**
     * 日志打印
     */
    private Logger log = LoggerFactory.getLogger(KeywordHandleFilter.class);

    /**
     * desc : 初始化
     * create_user : liziye
     * create_date : 2019/7/2
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("KeywordHandleFilter初始化");
    }

    /**
     * desc : 对关键词进行处理
     * create_user : liziye
     * create_date : 2019/7/2
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("KeywordHandleFilter执行过滤操作");
        chain.doFilter(request, response);
    }

    /**
     * desc : 销毁
     * create_user : liziye
     * create_date : 2019/7/2
     */
    @Override
    public void destroy() {
        log.info("KeywordHandleFilter銷毀");
    }
}
