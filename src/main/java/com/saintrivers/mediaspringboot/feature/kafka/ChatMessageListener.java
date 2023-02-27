package com.saintrivers.mediaspringboot.feature.kafka;

import com.saintrivers.mediaspringboot.feature.ChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "${custom.kafka.topic.chat}", groupId = "${custom.kafka.group}")
    public void listenChatMessage(ChatUseCase.ChatMessageRequest chatMessageRequest){
        messagingTemplate.convertAndSend("/topic/user/" + chatMessageRequest.getTargetChatId(), chatMessageRequest);
    }
}
