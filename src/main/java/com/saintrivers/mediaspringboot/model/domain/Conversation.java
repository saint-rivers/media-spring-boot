package com.saintrivers.mediaspringboot.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "chat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conversation {

    @Id
    @Column("chat_id")
    private Long id;

    @Column("group_name")
    private String groupName;

    @Column("group_profile")
    private String groupProfile;

    @Column("time_created")
    private LocalDateTime timeCreated;

    @Column("last_updated")
    private LocalDateTime lastUpdated;
}
