package com.saintrivers.mediaspringboot.feature.chat;

import com.saintrivers.mediaspringboot.model.domain.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {
}
