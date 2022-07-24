package com.socical.network.data.repositories;

import com.socical.network.data.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Reaction findByCode(String code);
}
