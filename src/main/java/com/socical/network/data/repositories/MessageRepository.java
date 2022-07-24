package com.socical.network.data.repositories;

import com.socical.network.data.entities.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

//    @Query(value = "SELECT Max(message) FROM Message message " +
//        "JOIN Messenger messenger ON message.sender.id = messenger.id " +
//        "JOIN Conversation conversation ON conversation.id = messenger.conversationId " +
//        "ORDER BY message.id DESC NULLS LAST")
//        // Please provide Pageable with page = 0, size = 1
//    Message findLastMessageConversation(Long conversationId);

    @Query(value = "SELECT max(message.id) FROM Message message WHERE message.sender.id = ?1")
    Long findLastMessageId(Long messengerId);

    @Query(value = "SELECT message FROM Message message WHERE message.sender.conversationId=:conversationId ORDER BY message.id DESC")
    List<Message> findAllByConversationId(@Param("conversationId") Long conversationId, Pageable pageable);

    @Query(value = "SELECT message FROM Message message WHERE message.sender.conversationId=:conversationId ORDER BY message.id DESC")
    List<Message> findAllByConversationId(@Param("conversationId") Long conversationId);

    @Query(value = "SELECT message FROM Message message WHERE message.sender.conversationId=?1 AND message.id > ?2 ORDER BY message.id DESC")
    List<Message> findAllByConversationIdMoreThanMessageId(Long conversationId, Long messageId);
}
