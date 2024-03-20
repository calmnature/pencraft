package com.example.pencraft.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ErrorController {
    @GetMapping("/error-400")
    public void error400(HttpServletResponse response) throws IOException{
        response.sendError(400,"400 오류");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException{
        response.sendError(500,"500 오류");
    }
}