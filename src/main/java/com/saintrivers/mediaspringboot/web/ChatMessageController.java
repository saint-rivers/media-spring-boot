package com.saintrivers.mediaspringboot.web;

import com.saintrivers.mediaspringboot.feature.ChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final KafkaTemplate<Integer, ChatUseCase.ChatMessageRequest> kafkaTemplate;

//    @PostMapping("/messages/send")
    @MessageMapping("/messages/send")
    public ResponseEntity<Void> sendMessage(@Payload ChatUseCase.ChatMessageRequest chatMessageRequest){
        System.out.println(chatMessageRequest);
        kafkaTemplate.send("chat-message", chatMessageRequest);
        return ResponseEntity.ok().build();
    }
//    @GetMapping("/messages/send")
//    public ResponseEntity<Void> sendMessage(){
//        kafkaTemplate.send("chat-message", new ChatUseCase.ChatMessageRequest(1L, "asd", LocalDateTime.now(), "sender", "target", "type"));
//        return ResponseEntity.ok().build();
//    }

}
