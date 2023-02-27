package com.saintrivers.mediaspringboot.web;

import com.saintrivers.mediaspringboot.feature.chat.ChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final KafkaTemplate<Integer, ChatUseCase.ChatMessageRequest> kafkaTemplate;

    @Value("${custom.kafka.topic.chat}")
    private String chatTopic;

    @MessageMapping("/messages/send")
    public ResponseEntity<Void> sendMessage(@Payload ChatUseCase.ChatMessageRequest chatMessageRequest) {
        kafkaTemplate.send(chatTopic, chatMessageRequest);
        return ResponseEntity.ok().build();
    }

}
