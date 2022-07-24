package com.socical.network.services;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.entities.MessageMedia;

import java.util.List;

public interface MessageMediaService {
    List<MessageMedia> createAll(List<MediaDto> mediaList);
}
