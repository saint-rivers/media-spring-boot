package com.saintrivers.mediaspringboot.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "user_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {

    @Column("chat_id")
    private Long chatId;

    @Column("user_id")
    private Long user_id;
}
