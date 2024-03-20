package com.example.pencraft.controller;

import com.example.pencraft.constant.SessionConst;
import com.example.pencraft.domain.Employees;
import com.example.pencraft.form.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping(value = {"/", "/home"})
    public String home(HttpServletRequest request, Model model, @RequestParam(name = "redirectURL", defaultValue = "/home")String redirectURL) {
        log.warn("홈컨트롤러 redirectURL = {}", redirectURL);
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("redirectURL", redirectURL);
        HttpSession session = request.getSession(false);
        log.warn("HomeController 실행");
        if (session == null) {
            log.warn("세션에 정보 없음");
            return "home";
        }

        Employees loginEmployees = (Employees) session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.warn("세션에 담긴 정보는 이거임 = {}", loginEmployees);
        if (loginEmployees == null) {
            log.warn("세션에 담긴 로그인 정보가 없음");
            return "home";
        }

        model.addAttribute("employees", loginEmployees);

        if (Objects.equals(loginEmployees.getRole(), "staff")) {
            log.warn("HomeController : loginStaffHome로 보낼거임");
            return "loginStaffHome";
        } else {
            log.warn("HomeController : loginAdminHome로 보낼거임");
            return "loginAdminHome";
        }
    }

    @GetMapping("/staff-page")
    public String roleCheckFail() {
        return "error/goBack";
    }

    @GetMapping("/teamlist")
    public String letmeintroduce() {
        return "teams/teamlist";
    }
}
