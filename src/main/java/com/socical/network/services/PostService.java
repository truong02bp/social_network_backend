package com.socical.network.services;

import com.socical.network.data.dto.PostDto;
import com.socical.network.data.entities.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Post create(PostDto postDto);
    List<Post> findAllByUserId(Long userId, Pageable pageable);

    List<Post> findTagPostByUserId(Long userId);
}
