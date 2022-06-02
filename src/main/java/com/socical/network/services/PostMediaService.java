package com.socical.network.services;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.entities.PostMedia;

import java.util.List;

public interface PostMediaService {
    List<PostMedia> createAll(List<MediaDto> medias);
}
