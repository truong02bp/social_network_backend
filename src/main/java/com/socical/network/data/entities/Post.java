package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post extends BaseEntity {

    private String caption;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "post_tag_user", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> tags;

    @ManyToMany
    @JoinTable(name = "post_interaction", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> interactBy;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<PostMedia> medias;
}
