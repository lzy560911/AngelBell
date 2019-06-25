package cn.angelbell.oa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication // Spring Boot项目的核心注解，主要目的是开启自动配置
@Controller // 标明这是一个SpringMVC的Controller控制器
public class HelloApplication{

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }
    
    /**
     * 1、 extends WebMvcConfigurationSupport 
     * 2、重写下面方法; 
     *      setUseSuffixPatternMatch: 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配； 
     *      setUseTrailingSlashMatch: 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
     */
    /*@Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
            .setUseSuffixPatternMatch(false)
            .setUseTrailingSlashMatch(true);
    }*/

    // 在main方法中启动一个应用，即：这个应用的入口
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}