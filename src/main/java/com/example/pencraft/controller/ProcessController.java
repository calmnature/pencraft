package com.example.pencraft.controller;

import com.example.pencraft.form.CountForm;
import com.example.pencraft.service.ProcessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class ProcessController {
    // 생산 공정 컨트롤러
    // 제품 생산, 생산 중단, 모니터링을 담당하는 컨트롤러
    private final ProcessService processService;

    @GetMapping("/order")
    public String showProcessForm(Model model) {
        model.addAttribute("countForm", new CountForm());
        return "products/start_process"; // Thymeleaf 템플릿 이름 반환
    }

    @PostMapping ("/order")
    public String startProcess(CountForm countForm) throws Exception {
        processService.processStart(countForm);
        return "redirect:/monitoring"; // 폼 페이지로 리다이렉트
    }

    @GetMapping("/monitoring")
    public String monitoring() {
        return "products/monitoring";
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopProduction() {
        try {
            processService.stopProduction();
            return ResponseEntity.ok("Production stopped successfully");
        } catch (Exception e) {
            log.error("Error stopping production", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error stopping production: " + e.getMessage());
        }
    }
}
