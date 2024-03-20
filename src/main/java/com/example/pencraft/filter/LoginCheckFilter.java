package com.example.pencraft.filter;

import com.example.pencraft.constant.SessionConst;
import com.example.pencraft.domain.Employees;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    //로그인이 필요하지 않은 페이지 url 요청들
    private static final String[] whitelist = {"/","/home", "/login", "/logout", "/static/img/*","/css/*", "/js/*", "/img/*", "/sockjs/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;




//        log.info("인증 체크 필터 시작");

        // whitelist에 있는 uri는 검증을 하지 않기때문에,
        // isLoginCheckPath 메서드에서 매칭되지 않는 경우만 가져온다.
        // 인증체크 로직 시작
        if (isLoginCheckPath(requestURI)) {
//            log.info("인증 체크 로직 실행 : " + requestURI);
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                // 로그인 되지 않음
//                log.info("미 인증 사용자 요청");
                // 로그인으로 redirect
                // 사용자가 원래 가려고 했던 URI를 파라미터 값으로 기억했다가 로그인 후,
                //이 페이지로 이동
                httpResponse.sendRedirect("/home?redirectURL=" + requestURI);
                log.info("requestURI : " + requestURI);
                // 미인증 사용자는 다음으로 진행하지 않고 끝낸다.
                return;
            }
        }
        // 로그인이 되어있다면 다음 단계로 넘어간다.
        chain.doFilter(request, response);
    }

    /*
     * 화이트 리스트의 경우 인증 체크 x
     * simpleMatch 	: 파라미터 문자열이 특정 패턴에 매칭되는지를 검사함.
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
        // 매칭이 되지 않을 때 검증을 해야하기 때문에 부정해준다.
    }
}
