package com.socical.network.services;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.entities.Media;

import java.util.Optional;

public interface MediaService {
    Media create(MediaDto mediaDto);
    Optional<Media> findByName(String name);

}
