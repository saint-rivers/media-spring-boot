package com.saintrivers.mediaspringboot.feature.message;


import com.saintrivers.mediaspringboot.model.domain.Message;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query("""
            SELECT * FROM
                (
                    SELECT * FROM chat_message m
                    INNER JOIN chat c ON c.chat_id = m.target_chat_id
                    WHERE c.chat_id = :conversationId
                    ORDER BY m.time_sent DESC
                    LIMIT :size
                    OFFSET (:page - 1) * :size
                ) AS results
            ORDER BY results.time_sent
            """)
    List<Message> findByConversationId(Long conversationId, int page, int size);
}
