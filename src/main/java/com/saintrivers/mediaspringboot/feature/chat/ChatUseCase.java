package com.saintrivers.mediaspringboot.feature.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public interface ChatUseCase {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ChatMessageRequest {
        private String content;
        private LocalDateTime timeSent;
        private String senderId;
        private String targetChatId;
        private String type;
    }

}
