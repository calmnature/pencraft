package com.example.pencraft.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebSocketSenderService {

    // 주기적으로 데이터를 보내기 위해 SimpleMessagingTemplate 변수 선언
    private final SimpMessagingTemplate messagingTemplate;

    // 생성자 주입을 통한 DI
    @Autowired
    public WebSocketSenderService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 파라미터로 수신한 data를 'destination 주소의 구독자들에게 메세지 발송
    public void sendMessageToClient(String destination, Map data) {
        messagingTemplate.convertAndSend(destination, data);
    }
}