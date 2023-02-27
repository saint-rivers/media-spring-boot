package com.saintrivers.mediaspringboot.feature.chat;

import com.saintrivers.mediaspringboot.feature.conversation.ConversationUseCase;
import com.saintrivers.mediaspringboot.model.domain.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMapper {

    public Chat toChat(ConversationUseCase.ConversationCreateRequest conversation) {
        var now = LocalDateTime.now();
        return Chat.builder()
                .id(null)
                .groupName(conversation.groupName())
                .groupProfile(conversation.groupProfile())
                .lastUpdated(now)
                .timeCreated(now)
                .build();
    }
}
