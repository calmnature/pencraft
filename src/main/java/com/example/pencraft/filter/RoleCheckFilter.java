package com.example.pencraft.filter;


import com.example.pencraft.constant.SessionConst;
import com.example.pencraft.domain.Employees;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class RoleCheckFilter implements Filter {


    private static final String[] blackList = {"/employees","/employees/new"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(isRoleCheckPath(requestURI)) {
            HttpSession session1 = httpRequest.getSession(false);
            Employees employees = (Employees) session1.getAttribute(SessionConst.LOGIN_MEMBER);
            String role = employees.getRole();
            if (role.equals("staff")) {
                httpResponse.sendRedirect("/staff-page");
                return;
            }
        }
        chain.doFilter(request, response);

    }
    private boolean isRoleCheckPath(String requestURI) {
        return PatternMatchUtils.simpleMatch(blackList, requestURI);
        // 매칭이 되지 않을 때 검증을 해야하기 때문에 부정해준다.
    }
}
