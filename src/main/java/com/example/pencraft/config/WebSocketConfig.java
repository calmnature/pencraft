package com.example.pencraft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //웹 소켓 메시지를 다룰 수 있게 허용
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) { // 메세지 브로커
        // 클라이언트가 구독할 주소의 접두사(Prefix)를 /process로 지정
        config.enableSimpleBroker("/process");
        // 당장 쓰이고 있지는 않지만 클라이언트가 서버에 데이터를 보낼 경우 접두사(Prefix)를 /client로 지정
        config.setApplicationDestinationPrefixes("/client");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 웹 소켓에 연결하기 위한 주소를 '/sockjs'로 지정
        registry.addEndpoint("/sockjs").withSockJS();
    }
}

