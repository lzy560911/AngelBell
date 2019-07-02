package cn.angelbell.oa.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.angelbell.oa.filter.KeywordHandleFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * desc : 过滤器配置
 * create_user : liziye
 * create_date : 2019.7.2
 */
@Configuration
public class FilterConfig {

    /**
     * 日志打印
     */
    private Logger log = LoggerFactory.getLogger(FilterConfig.class);

    /**
     * desc : 注册关键词处理过滤器
     * create_user : liziye
     * create_date : 2019.7.2
     */
    @Bean
    public FilterRegistrationBean registerKeywordHandleFilter () {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();

        KeywordHandleFilter keywordHandleFilter = new KeywordHandleFilter();

        // 设置过滤器名称, 执行顺序, 拦截url
        String filterName = keywordHandleFilter.getClass().getSimpleName();
        int order = 3;
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/");

        filterBean.setFilter(keywordHandleFilter);
        filterBean.setName(filterName);
        filterBean.setOrder(order);
        filterBean.setUrlPatterns(urlPatterns);

        log.info(String.format("注册过滤器, 过滤器name: %s, 过滤器order: %d, 过滤器过滤url: %s", filterName, order, urlPatterns));
        return filterBean;
    }

}
