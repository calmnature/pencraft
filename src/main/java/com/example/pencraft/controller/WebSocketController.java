package com.example.pencraft.controller;

import com.example.pencraft.service.LotService;
import com.example.pencraft.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
@RequiredArgsConstructor
public class WebSocketController {
    private final ProcessService processService;
    private final LotService lotService;

    @MessageMapping("/request")
    public void responseLastData() {
        processService.sendLastDataMap();
    }

    @MessageMapping("/request/alldate")
    public void responseAllDateData() {
        lotService.allDateSend();
    }
}
