package com.socical.network.data.repositories;

import com.socical.network.data.entities.FollowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRequestRepository extends JpaRepository<FollowRequest, Long> {
}
