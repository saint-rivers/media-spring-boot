package com.saintrivers.mediaspringboot.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "user_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGroup {

    @Id
    @Column("id")
    private Long id;

    @Column("chat_id")
    private Long chatId;

    @Column("user_id")
    private UUID user_id;
}
