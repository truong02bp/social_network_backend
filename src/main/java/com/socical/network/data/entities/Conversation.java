package com.socical.network.data.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "conversations")
@Getter
@Setter
public class Conversation extends BaseEntity {

    @Column(name = "color")
    private String color;

    @Column(name = "name")
    private String name;

    @Column(name = "is_group")
    private Boolean isGroup = false;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media image;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "conversation_id")
    private List<Messenger> messengers;

    private Long lastMessageId;
}
