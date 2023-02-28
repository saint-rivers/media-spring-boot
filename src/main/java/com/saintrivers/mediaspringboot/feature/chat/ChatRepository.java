package com.saintrivers.mediaspringboot.feature.chat;

import com.saintrivers.mediaspringboot.model.domain.Conversation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends CrudRepository<Conversation, Long> {

    @Query("""
            SELECT c.* FROM user_group u
            INNER JOIN chat c
            ON c.chat_id = u.chat_id
            WHERE u.user_id = :userId
            """)
    List<Conversation> findAllByUserId(UUID userId);
}
