package com.socical.network.data.repositories;

import com.socical.network.data.entities.Conversation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    @Query(value = "select conversion from Conversation conversion " +
        "join Messenger messenger on messenger.conversationId = conversion.id and messenger.user.id = ?1 order by conversion.lastModifiedDate desc")
    List<Conversation> findByUserId(@Param("userId") Long userId, Pageable pageable);
}
