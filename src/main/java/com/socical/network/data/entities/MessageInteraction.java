package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "message_interaction")
@Getter
@Setter
public class MessageInteraction extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "messenger_id", nullable = false)
    private Messenger seenBy;

    @ManyToOne
    @JoinColumn(name = "reaction_id")
    private Reaction reaction;

}
