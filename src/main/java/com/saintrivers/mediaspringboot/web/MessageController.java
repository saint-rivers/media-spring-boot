package com.saintrivers.mediaspringboot.web;

import com.saintrivers.mediaspringboot.feature.message.MessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageUseCase messageUseCase;

    @GetMapping("/conversation/{id}")
    public List<MessageUseCase.MessageResponse> getMessages(@PathVariable Long id) {
        return messageUseCase.getMessagesByConversationId(id);
    }
}
