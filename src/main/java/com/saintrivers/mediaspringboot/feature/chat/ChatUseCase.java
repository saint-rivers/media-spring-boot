package com.saintrivers.mediaspringboot.feature.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ChatUseCase {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ChatMessageRequest {
        private String content;
        private LocalDateTime timeSent;
        private UUID senderId;
        private Long targetChatId;
        private String type;
    }


}
