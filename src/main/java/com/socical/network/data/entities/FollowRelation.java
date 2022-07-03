package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "follow_relation", indexes = {
    @Index(name = "follow_user_follower", columnList = "user_id, follower_id", unique = true)
})
@Getter
@Setter
public class FollowRelation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;
}
