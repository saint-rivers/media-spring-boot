package com.saintrivers.mediaspringboot.feature.conversation;

import com.saintrivers.mediaspringboot.model.domain.Conversation;
import com.saintrivers.mediaspringboot.model.domain.UserGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationMapper {

    public ConversationUseCase.ConversationResponse toConversationResponse(Conversation conversation) {
        return ConversationUseCase.ConversationResponse.builder()
                .chatId(conversation.getId())
                .members(null)
                .groupName(conversation.getGroupName())
                .groupProfile(conversation.getGroupProfile())
                .build();
    }

    public ConversationUseCase.ConversationResponse toConversationResponse(Conversation conversation, List<UserGroup> userGroups, UUID subject) {
        List<UUID> members = userGroups.stream().map(UserGroup::getUser_id).collect(Collectors.toList());
        members.add(subject);

        return ConversationUseCase.ConversationResponse.builder()
                .chatId(conversation.getId())
                .members(members)
                .groupName(conversation.getGroupName())
                .groupProfile(conversation.getGroupProfile())
                .build();
    }
}
