package com.socical.network.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.entities.Media;
import com.socical.network.data.repositories.MediaRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.MediaService;
import com.socical.network.services.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MinioService minioService;
    private final MediaRepository mediaRepository;
    private final ObjectMapper mapper;

    @Override
    public Media create(MediaDto mediaDto) {
        if (mediaDto.getBytes() == null) {
            throw BusinessException.builder().status(HttpStatus.BAD_REQUEST).message("Bytes media must not be empty").build();
        }

        final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).replaceAll("-", "");
        Media media = mapper.convertValue(mediaDto, Media.class);
        String folder = "/" + time + "/";
        minioService.upload(folder, mediaDto.getName(), new ByteArrayInputStream(mediaDto.getBytes()));
        media.setUrl(folder + media.getName());
        Media existedMedia = mediaRepository.findByName(media.getName()).orElse(null);
        if (existedMedia != null) {
            existedMedia.setUrl(media.getUrl());
            return mediaRepository.save(existedMedia);
        }
        return mediaRepository.save(media);
    }
}
