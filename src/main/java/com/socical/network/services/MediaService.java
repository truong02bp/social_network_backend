package com.socical.network.services;

import com.socical.network.data.dto.MediaDto;
import com.socical.network.data.entities.Media;

public interface MediaService {

    Media create(MediaDto mediaDto);

}
