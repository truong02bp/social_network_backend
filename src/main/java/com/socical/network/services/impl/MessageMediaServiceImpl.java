package com.socical.network.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.entities.Media;
import com.socical.network.data.entities.MessageMedia;
import com.socical.network.data.repositories.MessageMediaRepository;
import com.socical.network.exceptions.BusinessException;
import com.socical.network.services.MessageMediaService;
import com.socical.network.services.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageMediaServiceImpl implements MessageMediaService {

    private final ObjectMapper mapper;
    private final MinioService minioService;
    private final MessageMediaRepository messageMediaRepository;

    @Override
    public List<MessageMedia> createAll(List<MediaDto> mediaList) {
        List<MessageMedia> messageMedias = new ArrayList<>();
        for (MediaDto mediaDto : mediaList) {
            if (mediaDto.getBytes() == null) {
                throw BusinessException.builder().status(HttpStatus.BAD_REQUEST).message("Bytes media must not be empty").build();
            }
            final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).replaceAll("-", "");
            MessageMedia messageMedia = mapper.convertValue(mediaDto, MessageMedia.class);
            String folder = "/" + time + "/";
            minioService.upload(folder, mediaDto.getName(), new ByteArrayInputStream(mediaDto.getBytes()));
            messageMedia.setContentType(mediaDto.getName().substring(mediaDto.getName().lastIndexOf(".")+1));
            messageMedia.setUrl(folder + messageMedia.getName());
            messageMedias.add(messageMediaRepository.save(messageMedia));
        }
        return messageMedias;
    }

}
