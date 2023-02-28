package com.saintrivers.mediaspringboot.web;

import com.saintrivers.mediaspringboot.feature.conversation.ConversationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conversations")
public class ConversationController {

    private final ConversationUseCase conversationUseCase;

    @GetMapping("/user/{id}")
    public List<ConversationUseCase.ConversationResponse> getConversations(@PathVariable UUID id) {
        return conversationUseCase.getByUserId(id);
    }

    @GetMapping("/{id}")
    public ConversationUseCase.ConversationResponse getChatById(@PathVariable Long id) {
        return conversationUseCase.getByConversationId(id);
    }

    @PostMapping
    public ResponseEntity<Void> createConversation(
            @RequestBody ConversationUseCase.ConversationCreateRequest conversationCreateRequest
    ) {
        Long conversationId = conversationUseCase.create(conversationCreateRequest);
        return ResponseEntity.created(URI.create("/api/v1/conversations/" + conversationId)).build();
    }
}
