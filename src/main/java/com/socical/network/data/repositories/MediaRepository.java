package com.socical.network.data.repositories;

import com.socical.network.data.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByName(String name);
}
