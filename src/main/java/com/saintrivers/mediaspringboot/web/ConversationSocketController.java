package com.saintrivers.mediaspringboot.web;

import com.saintrivers.mediaspringboot.feature.chat.ChatUseCase;
import com.saintrivers.mediaspringboot.feature.message.MessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ConversationSocketController {

    private final KafkaTemplate<Integer, ChatUseCase.ChatMessageRequest> kafkaTemplate;
    private final KafkaTemplate<Integer, MessageUseCase.MessageRequest> kafkaMessageTemplate;

    @Value("${custom.kafka.topic.chat}")
    private String chatTopic;

    @MessageMapping("/messages/send")
    public ResponseEntity<Void> sendMessage(@Payload ChatUseCase.ChatMessageRequest chatMessageRequest) {
        log.debug(chatMessageRequest.toString());
        kafkaTemplate.send(chatTopic, chatMessageRequest);
        return ResponseEntity.ok().build();
    }

    @MessageMapping("/v2/messages/send")
    public ResponseEntity<Void> sendMessageV2(@Payload MessageUseCase.MessageRequest chatMessageRequest) {
        log.debug(chatMessageRequest.toString());
        kafkaMessageTemplate.send(chatTopic + ".2", chatMessageRequest);
        return ResponseEntity.ok().build();
    }
}
