package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "story_interaction")
@Getter
@Setter
public class StoryInteraction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "reaction_id")
    private Reaction reaction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
