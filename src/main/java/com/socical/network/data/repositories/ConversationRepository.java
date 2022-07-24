package com.socical.network.data.repositories;

import com.socical.network.data.entities.Conversation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    @Query(value = "SELECT conversion FROM Conversation conversion " +
        "JOIN Messenger messenger ON messenger.conversationId = conversion.id AND messenger.user.id = ?1 ORDER BY conversion.lastModifiedDate DESC")
    List<Conversation> findByUserId(@Param("userId") Long userId, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Conversation SET lastMessageId = ?2, lastModifiedDate = CURRENT_TIMESTAMP WHERE id = ?1")
    int rowUpdate(Long conversationId, Long messageId);
}
