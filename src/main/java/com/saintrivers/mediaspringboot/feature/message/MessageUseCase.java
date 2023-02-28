package com.saintrivers.mediaspringboot.feature.message;

import com.saintrivers.mediaspringboot.feature.chat.ChatUseCase;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageUseCase {

    List<MessageResponse> getMessagesByConversationId(Long conversationId);

    MessageResponse insertMessage(ChatUseCase.ChatMessageRequest chatMessageRequest);

    MessageResponse insertMessage(MessageRequest chatMessageRequest);

    record MessageRequest(String content, LocalDateTime timeSent, String type, Long targetConversationId) {
    }

    @Builder
    record MessageResponse(Long messageId, String content, LocalDateTime timeSent, String senderId,
                           Long targetConversationId, String type) {
    }
}
