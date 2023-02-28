package com.saintrivers.mediaspringboot.feature.message;

import com.saintrivers.mediaspringboot.feature.chat.ChatUseCase;
import com.saintrivers.mediaspringboot.model.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService implements MessageUseCase {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public List<MessageResponse> getMessagesByConversationId(Long conversationId) {
        return messageRepository.findByConversationId(conversationId, 1, 3)
                .stream()
                .map(messageMapper::toMessageResponse)
                .toList();
    }

    @Override
    public MessageResponse insertMessage(MessageRequest chatMessageRequest) {
        Message message = messageMapper.toMessage(chatMessageRequest, UUID.fromString("d4b6af2c-3cb5-4263-8ea7-d092cfddede5"));
        var payload = messageRepository.save(message);
        return messageMapper.toMessageResponse(payload);
    }

    @Override
    public MessageResponse insertMessage(ChatUseCase.ChatMessageRequest chatMessageRequest) {
        Message message = messageMapper.toMessage(chatMessageRequest, UUID.fromString("d4b6af2c-3cb5-4263-8ea7-d092cfddede5"));
        var payload = messageRepository.save(message);
        return messageMapper.toMessageResponse(payload);
    }
}
