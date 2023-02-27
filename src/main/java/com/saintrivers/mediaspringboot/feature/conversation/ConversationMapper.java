package com.saintrivers.mediaspringboot.feature.conversation;

import com.saintrivers.mediaspringboot.model.domain.Chat;
import com.saintrivers.mediaspringboot.model.domain.UserGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationMapper {

    public ConversationUseCase.ConversationResponse toConversationResponse(Chat chat, List<UserGroup> userGroups, UUID subject) {
        List<UUID> members = userGroups.stream().map(UserGroup::getUser_id).collect(Collectors.toList());
        members.add(subject);

        return ConversationUseCase.ConversationResponse.builder()
                .members(members)
                .groupName(chat.getGroupName())
                .groupProfile(chat.getGroupProfile())
                .build();
    }
}
