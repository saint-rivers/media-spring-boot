package com.saintrivers.mediaspringboot.feature.conversation;

import com.saintrivers.mediaspringboot.feature.chat.ChatMapper;
import com.saintrivers.mediaspringboot.feature.chat.ChatRepository;
import com.saintrivers.mediaspringboot.model.domain.Chat;
import com.saintrivers.mediaspringboot.model.domain.UserGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversationService implements ConversationUseCase {

    // repositories
    private final ChatRepository chatRepository;
    private final UserGroupRepository userGroupRepository;

    // mappers
    private final UserGroupMapper userGroupMapper;
    private final ChatMapper chatMapper;
    private final ConversationMapper conversationMapper;

    // others
    private final KafkaTemplate<Integer, ConversationResponse> conversationSenderTemplate;

    @Override
    public void create(ConversationCreateRequest conversationCreateRequest) {
        Chat chat = chatMapper.toChat(conversationCreateRequest);
        chat = chatRepository.save(chat);

        List<UserGroup> userGroups = userGroupMapper.toUserGroups(chat, conversationCreateRequest.memberIds());
        userGroupRepository.saveAll(userGroups);

        UUID subject = UUID.randomUUID();
        var conversation = conversationMapper.toConversationResponse(chat, userGroups, subject);

        conversationSenderTemplate.send("conversation" , conversation);
    }
}
