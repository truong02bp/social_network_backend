package com.socical.network.data.repositories;

import com.socical.network.data.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT post FROM Post post where post.user.id = ?1")
    List<Post> findByUserId(Long userId);

}
