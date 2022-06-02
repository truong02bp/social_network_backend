package com.socical.network.data.repositories;

import com.socical.network.data.entities.FollowRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRelationRepository extends JpaRepository<FollowRelation, Long> {
}
