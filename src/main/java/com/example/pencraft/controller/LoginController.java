package com.example.pencraft.controller;

import com.example.pencraft.constant.SessionConst;
import com.example.pencraft.domain.Employees;
import com.example.pencraft.form.LoginForm;
import com.example.pencraft.service.LoginService;
import jakarta.servlet.http.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

//    @GetMapping("/")
//    public String loginForm(Model model) {
//        model.addAttribute("loginForm", new LoginForm());
//        return "home";
//    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletRequest request, @RequestParam(name = "redirectURL", defaultValue = "/home")String redirectURL) {
        log.warn("POST로 /login 들어옴!");
        log.warn("request.getRequestURI() = {}", request.getRequestURI());
        if (bindingResult.hasErrors()) {
            log.info("해즈에러");
            return "home";
        }
        log.warn("사용자가 입력한 값 = {}",form);
        Employees loginEmployees = loginService.login(form.getEmployees_id(), form.getPassword());

        log.warn("DB에서 비교해봄 = {}", loginEmployees);

        if (loginEmployees == null) {
            bindingResult.reject("loginFail", "사번 또는 비밀번호가 맞지 않습니다");
            return "home";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginEmployees);
        log.warn("Session에 정보 담음 = {}", loginEmployees);

        log.info("리다이렉트 : " + redirectURL);
        return "redirect:" + redirectURL;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        //세션을 삭제한다.
        HttpSession session = request.getSession(false);
        //true는 세션이 없으면 만들어 버린다., 일단 가지고 오는데 없으면 null

        if(session != null) {
            session.invalidate();   //세션을 제거한다.
        }

        return "redirect:/";
    }
}
