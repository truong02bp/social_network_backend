package com.socical.network.data.repositories;

import com.socical.network.data.entities.Messenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessengerRepository extends JpaRepository<Messenger, Long> {

    @Query(value = "SELECT messenger FROM Messenger messenger WHERE messenger.conversationId = ?1 AND messenger.id <> ?2")
    List<Messenger> findByConversationIdExceptMessengerId(Long conversationId, Long messengerId);
}
