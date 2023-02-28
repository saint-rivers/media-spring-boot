package com.saintrivers.mediaspringboot.feature.chat;

import com.saintrivers.mediaspringboot.feature.message.MessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageUseCase messageUseCase;
//    private final MessageMapper messageMapper;

    @KafkaListener(topics = "${custom.kafka.topic.chat}" + ".2", groupId = "${custom.kafka.group}", containerFactory = "kafkaMessageListenerContainerFactory")
    public void listenMessages(MessageUseCase.MessageRequest chatMessageRequest) {
        var payload = messageUseCase.insertMessage(chatMessageRequest);
        messagingTemplate.convertAndSend("/topic/user/" + payload.targetConversationId(), payload);
    }

    @KafkaListener(topics = "${custom.kafka.topic.chat}", groupId = "${custom.kafka.group}", containerFactory = "kafkaChatListenerContainerFactory")
    public void listen(ChatUseCase.ChatMessageRequest chatMessageRequest) {
        messagingTemplate.convertAndSend("/topic/user/" + chatMessageRequest.getTargetChatId(), chatMessageRequest);
        messageUseCase.insertMessage(chatMessageRequest);
    }

//    @KafkaListener(topics = "${custom.kafka.topic.chat}", groupId = "${custom.kafka.group}")
//    public void listenChatMessage(ChatUseCase.ChatMessageRequest chatMessageRequest){
//        // find users associated with target chat and send to each of them
//
//        messagingTemplate.convertAndSend("/topic/user/" + chatMessageRequest.getTargetChatId(), chatMessageRequest);
//        messageUseCase.insertMessage(chatMessageRequest);
//    }
}
