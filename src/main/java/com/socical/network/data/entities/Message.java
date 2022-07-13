package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message extends BaseEntity {

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Messenger sender;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id")
    private List<MessageInteraction> interactions;

}
