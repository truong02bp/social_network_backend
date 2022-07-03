package com.socical.network.data.repositories;

import com.socical.network.data.entities.FollowRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRequestRepository extends JpaRepository<FollowRequest, Long> {

    @Query(value = "SELECT request FROM FollowRequest request where request.receiver.id = ?1")
    List<FollowRequest> findByUserId(Long userId);

    @Query(value = "SELECT count(request.id) FROM FollowRequest request where request.receiver.id = ?1")
    int countByUserId(Long userId);
}
