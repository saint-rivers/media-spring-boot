package com.saintrivers.mediaspringboot.feature.message;

import com.saintrivers.mediaspringboot.feature.chat.ChatUseCase;
import com.saintrivers.mediaspringboot.model.domain.Message;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageMapper {
    public Message toMessage(ChatUseCase.ChatMessageRequest message, UUID tokenSubject) {
        return Message.builder()
                .content(message.getContent())
                .targetConversationId(message.getTargetChatId())
                .timeSent(message.getTimeSent())
                .senderId(tokenSubject)
                .build();
    }
    public Message toMessage(MessageUseCase.MessageRequest message, UUID tokenSubject) {
        return Message.builder()
                .content(message.content())
                .targetConversationId(message.targetConversationId())
                .timeSent(message.timeSent())
                .senderId(tokenSubject)
                .type(message.type())
                .build();
    }

    public MessageUseCase.MessageResponse toMessageResponse(Message message) {
        return MessageUseCase.MessageResponse.builder()
                .messageId(message.getMessageId())
                .content(message.getContent())
                .targetConversationId(message.getTargetConversationId())
                .timeSent(message.getTimeSent())
                .type(message.getType())
                .senderId(message.getSenderId().toString())
                .build();
    }

}
