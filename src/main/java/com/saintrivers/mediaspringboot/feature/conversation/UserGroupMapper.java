package com.saintrivers.mediaspringboot.feature.conversation;

import com.saintrivers.mediaspringboot.model.domain.Chat;
import com.saintrivers.mediaspringboot.model.domain.UserGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserGroupMapper {

    public List<UserGroup> toUserGroups(Chat chat, List<UUID> memberIds) {
        return memberIds.stream()
                .map(member -> UserGroup.builder()
                        // get user from context
                        .user_id(UUID.randomUUID())
                        .chatId(chat.getId())
                        .build())
                .toList();
    }
}
