package com.socical.network.services;

import com.socical.network.data.dto.PostDto;
import com.socical.network.data.entities.Post;

import java.util.List;

public interface PostService {

    Post create(PostDto postDto);
    List<Post> findAllByUserId(Long userId);
}
