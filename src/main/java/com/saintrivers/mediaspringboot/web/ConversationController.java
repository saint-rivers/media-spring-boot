package com.saintrivers.mediaspringboot.web;

import com.saintrivers.mediaspringboot.feature.conversation.ConversationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conversations")
@CrossOrigin("http://localhost:4200")
public class ConversationController {

    private final ConversationUseCase conversationUseCase;

    @PostMapping
    public ResponseEntity<Void> createConversation(
            @RequestBody ConversationUseCase.ConversationCreateRequest conversationCreateRequest
    ) {
        conversationUseCase.create(conversationCreateRequest);
        return ResponseEntity.created(URI.create("/api/v1/conversations/" + 1)).build();
    }
}
