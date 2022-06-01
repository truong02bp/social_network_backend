package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment extends BaseEntity {

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "comment_reply", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "comment_reply_id"))
    private List<Comment> replies;

    @ManyToMany
    @JoinTable(name = "comment_interaction", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> interactBy;
}
