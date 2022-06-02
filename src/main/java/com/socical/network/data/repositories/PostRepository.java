package com.socical.network.data.repositories;

import com.socical.network.data.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT post FROM Post post where post.user.id = ?1")
    List<Post> findByUserId(Long userId);

    @Query(value = "select * from posts where id in (select posts.id from posts " +
        "inner join post_tag_user on post_tag_user.post_id = posts.id and post_tag_user.user_id = ?1)", nativeQuery = true)
    List<Post> findTagPostByUserId(Long userId);
}
