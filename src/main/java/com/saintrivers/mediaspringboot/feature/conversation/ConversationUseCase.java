package com.saintrivers.mediaspringboot.feature.conversation;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

public interface ConversationUseCase {

    void create(ConversationCreateRequest conversationCreateRequest);

    List<ConversationResponse> getByUserId(UUID id);

    record ConversationCreateRequest(String groupName, String groupProfile, List<UUID> memberIds) {
    }

    @Builder
    record ConversationResponse(String groupName, String groupProfile, List<UUID> members) {
    }
}
