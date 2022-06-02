package com.socical.network.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.entities.Media;
import com.socical.network.data.entities.PostMedia;
import com.socical.network.data.repositories.PostMediaRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.MediaService;
import com.socical.network.services.MinioService;
import com.socical.network.services.PostMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostMediaServiceImpl implements PostMediaService {

    private final MinioService minioService;
    private final ObjectMapper mapper;
    private final PostMediaRepository postMediaRepository;


    @Override
    public List<PostMedia> createAll(List<MediaDto> medias) {
        List<PostMedia> a = medias.stream().map(this::create).toList();
        return a;
    }

    public PostMedia create(MediaDto mediaDto) {
        if (mediaDto.getBytes() == null) {
            throw BusinessException.builder().status(HttpStatus.BAD_REQUEST).message("Bytes media must not be empty").build();
        }

        final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).replaceAll("-", "");
        PostMedia media = mapper.convertValue(mediaDto, PostMedia.class);
        String folder = "/post" + time + "/";
        minioService.upload(folder, mediaDto.getName(), new ByteArrayInputStream(mediaDto.getBytes()));
        media.setUrl(folder + media.getName());
        return postMediaRepository.save(media);
    }
}
