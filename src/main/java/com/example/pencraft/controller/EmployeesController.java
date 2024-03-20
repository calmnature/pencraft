package com.example.pencraft.controller;

import com.example.pencraft.constant.SessionConst;
import com.example.pencraft.domain.Employees;
import com.example.pencraft.form.EmployeesForm;
import com.example.pencraft.service.EmployeesService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesService employeesService;

    @GetMapping("/employees/new")
    public String createForm(Model model) {
        EmployeesForm form = new EmployeesForm();
        form.setId(employeesService.findMaxId() + 1);
        model.addAttribute("employeesForm", form);
        return "employees/create_employee";
    }

    @PostMapping("/employees/new")
    public ResponseEntity<String> create(EmployeesForm form) {
        log.info("사용자 입력 = " + form);
        form.setPassword(String.valueOf(form.getId()));
        Long id = employeesService.save(Employees.toEntity(form));
        if(id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원 등록에 실패하였습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("회원 등록에 성공하였습니다.");
    }

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employees> employeesList = employeesService.findEmployees();

        List<EmployeesForm> empFormList = new ArrayList<>();
        for (Employees emp : employeesList) {
            empFormList.add(EmployeesForm.toDto(emp));
        }
        model.addAttribute("employeesList", empFormList);
        return "employees/show_employee_list";
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<String> updatetoPassword(@PathVariable(name = "id") String id, HttpSession httpSession){
        log.info("수정할 아이디 = " + id);
        Employees save = employeesService.updatePassword(Long.parseLong(id));
        if(save == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("초기화 실패");
        }
        return ResponseEntity.status(HttpStatus.OK).body("초기화 성공");
    }

    @GetMapping("/employees/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name=  "id" ) Long id){
        log.info("delete컨트롤러 들어옴?");
        boolean check = employeesService.deleteEmployee(id);
        if(check) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/change-password")
    public String changePasswordForm() {
        return "employees/change_password";
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("password")String password, HttpSession httpSession) {
        Employees employees = (Employees) httpSession.getAttribute("loginMember");
        log.info("password = {}, employees = {}", password, employees);
        if(employees.getPassword().equals(password)) {
            httpSession.setAttribute("check", true);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/update-password")
    public String updatePasswordForm(HttpSession httpSession){
        if(!(boolean)httpSession.getAttribute("check")){
            return "redirect:/error-400";
        }
        return "employees/input_password";
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam("password")String password, HttpSession httpSession){
        Employees employees = (Employees) httpSession.getAttribute("loginMember");
        log.info("employees = {} / password = {}", employees, password);
        Employees modify = employeesService.modifyPassword(employees, password);
        httpSession.setAttribute("check", false);
        httpSession.setAttribute(SessionConst.LOGIN_MEMBER, modify);
        return "redirect:/";
    }

}
