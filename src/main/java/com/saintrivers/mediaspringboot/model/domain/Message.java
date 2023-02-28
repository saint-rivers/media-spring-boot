package com.saintrivers.mediaspringboot.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "chat_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    @Id
    @Column("message_id")
    private Long messageId;

    @Column("content")
    private String content;

    @Column("time_sent")
    private LocalDateTime timeSent;

    @Column("sender_id")
    private UUID senderId;

    @Column("target_chat_id")
    private Long targetConversationId;

    @Column("type")
    private String type;
}
