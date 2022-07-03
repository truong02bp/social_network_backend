package com.socical.network.data.repositories;

import com.socical.network.data.entities.FollowRelation;
import com.socical.network.data.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRelationRepository extends JpaRepository<FollowRelation, Long> {

    @Query(value = "SELECT follow.user FROM FollowRelation follow WHERE follow.follower.id = ?1 AND follow.user.username LIKE %?2%")
    List<User> findFollowingByUserId(Long userId, String username, Pageable pageable);

    @Query(value = "SELECT count(follow.user.id) FROM FollowRelation follow WHERE follow.follower.id = ?1")
    int countFollowingByUserId(Long userId);

    @Query(value = "SELECT follow.follower FROM FollowRelation follow WHERE follow.user.id = ?1 AND follow.follower.username LIKE %?2%")
    List<User> findFollowersByUserId(Long userId, String username, Pageable pageable);

    @Query(value = "SELECT count(follow.follower.id) FROM FollowRelation follow WHERE follow.user.id = ?1")
    int countFollowersByUserId(Long userId);

    @Query(value = "SELECT count(follow.id) FROM FollowRelation follow WHERE follow.follower.id = ?1 AND follow.user.id = ?2")
    int checkFollowing(Long userId, Long followerId);

    @Modifying
    @Query(value = "DELETE FROM FollowRelation relation WHERE relation.user.id = ?1 and relation.follower.id = ?2")
    int deleteByUserIdAndFollowerId(Long userId, Long followerId);
}
