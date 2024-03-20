package com.example.pencraft.config;

import com.example.pencraft.filter.LogFilter;
import com.example.pencraft.filter.LoginCheckFilter;
import com.example.pencraft.filter.RoleCheckFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<Filter> logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();

        filterRegistrationBean.setFilter(new LogFilter());	// LogFilter등록
        filterRegistrationBean.setOrder(1);	// 먼저 적용할 필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); //모든 url 다 적용 -> 모든 요청에 다 필터 적용
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();

        filterRegistrationBean.setFilter(new LoginCheckFilter());	// LoginCheckFilter등록
        filterRegistrationBean.setOrder(2);	// 먼저 등록할 필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); //모든 url 다 적용 -> 모든 요청에 다 필터 적용
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> RoleCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();

        filterRegistrationBean.setFilter(new RoleCheckFilter());	// RoleCheckFilter등록
        filterRegistrationBean.setOrder(3);	// 먼저 등록할 필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); //모든 url 다 적용 -> 모든 요청에 다 필터 적용
        return filterRegistrationBean;
    }


}
