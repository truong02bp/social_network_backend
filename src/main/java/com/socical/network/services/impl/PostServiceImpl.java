package com.socical.network.services.impl;

import com.socical.network.data.dto.PostDto;
import com.socical.network.data.entities.Post;
import com.socical.network.data.entities.PostMedia;
import com.socical.network.data.entities.User;
import com.socical.network.data.repositories.PostMediaRepository;
import com.socical.network.data.repositories.PostRepository;
import com.socical.network.data.repositories.UserRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.PostMediaService;
import com.socical.network.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMediaService postMediaService;

    @Override
    public Post create(PostDto postDto) {
        // create media post async
        CompletableFuture<List<PostMedia>> createMedias = CompletableFuture.supplyAsync(() -> postMediaService.createAll(postDto.getMedias()));

        List<User> tags = userRepository.findAllById(postDto.getTags());
        User user = userRepository.findById(postDto.getUserId()).orElseThrow(() -> {
            throw BusinessException.builder().message("User not found with id {" + postDto.getUserId() + "}").status(HttpStatus.NOT_FOUND).build();
        });
        Post post = new Post();
        post.setTags(tags);
        post.setCaption(postDto.getCaption());
        post.setUser(user);
        try {
            post.setMedias(createMedias.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw BusinessException.builder().message("Create post error!").status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAllByUserId(Long userId, Pageable pageable) {
        return postRepository.findByUserId(userId, pageable);
    }

    @Override
    public List<Post> findTagPostByUserId(Long userId) {
        return postRepository.findTagPostByUserId(userId);
    }
}
