package com.saintrivers.mediaspringboot.feature.chat;

import com.saintrivers.mediaspringboot.feature.conversation.ConversationUseCase;
import com.saintrivers.mediaspringboot.model.domain.Conversation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMapper {

    public Conversation toChat(ConversationUseCase.ConversationCreateRequest conversation) {
        var now = LocalDateTime.now();
        return Conversation.builder()
                .id(null)
                .groupName(conversation.groupName())
                .groupProfile(conversation.groupProfile())
                .lastUpdated(now)
                .timeCreated(now)
                .build();
    }

    public ConversationUseCase.ConversationResponse toChatResponse(Conversation conversation) {
        return ConversationUseCase.ConversationResponse.builder()
                .chatId(conversation.getId())
                .groupProfile(conversation.getGroupProfile())
                .groupName(conversation.getGroupName())
                .build();
    }
}
