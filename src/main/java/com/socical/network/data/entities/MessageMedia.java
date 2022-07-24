package com.socical.network.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "message_media")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageMedia extends BaseMediaEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Message message;
}
