package com.saintrivers.mediaspringboot.feature.conversation;

import com.saintrivers.mediaspringboot.model.domain.UserGroup;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
}
