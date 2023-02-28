package com.saintrivers.mediaspringboot.feature.conversation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public interface ConversationUseCase {

    Long create(ConversationCreateRequest conversationCreateRequest);

    List<ConversationResponse> getByUserId(UUID id);

    ConversationResponse getByConversationId(Long id);

    record ConversationCreateRequest(String groupName, String groupProfile, List<UUID> memberIds) {
    }

    @Builder
    record ConversationResponse(
            Long chatId,
            String groupName,
            String groupProfile,
            @JsonInclude(JsonInclude.Include.NON_NULL) List<UUID> members
    ) {
    }
}
