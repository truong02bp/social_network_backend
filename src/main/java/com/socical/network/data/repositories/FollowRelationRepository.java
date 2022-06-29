package com.socical.network.data.repositories;

import com.socical.network.data.entities.FollowRelation;
import com.socical.network.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRelationRepository extends JpaRepository<FollowRelation, Long> {

    @Query(value = "SELECT follow.user FROM FollowRelation follow where follow.follower.id = ?1")
    List<User> findFollowingByUserId(Long userId);

    @Query(value = "SELECT count(follow.user.id) FROM FollowRelation follow where follow.follower.id = ?1")
    int countFollowingByUserId(Long userId);

    @Query(value = "SELECT follow.follower FROM FollowRelation follow where follow.user.id = ?1")
    List<User> findFollowersByUserId(Long userId);

    @Query(value = "SELECT count(follow.follower.id) FROM FollowRelation follow where follow.user.id = ?1")
    int countFollowersByUserId(Long userId);
}
