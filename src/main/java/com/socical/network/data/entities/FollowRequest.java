package com.socical.network.data.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "follow_request", uniqueConstraints = @UniqueConstraint(
    columnNames = {"sender_id", "receiver_id"}
))
@Getter
@Setter
public class FollowRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    @Schema(hidden = true)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

}
