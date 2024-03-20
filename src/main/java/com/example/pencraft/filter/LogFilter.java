package com.example.pencraft.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // ServletRequest -> HttpServletRequest 로 명시적 형변환 해주기
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String requestURI = httpServletRequest.getRequestURI();

//        log.info("requestURI : " + requestURI);

        chain.doFilter(request, response); //다음 서블릿 요청으로 넘김

//        log.info("responseURI : " + requestURI);
    }

}
