package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "story")
@Getter
@Setter
public class Story extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "story_id")
    private List<StoryMedia> medias;

    @OneToMany
    @JoinColumn(name = "story_id")
    private List<StoryInteraction> interactions;

}
