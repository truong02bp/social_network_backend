package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "message_media")
@Entity
@Getter
@Setter
public class MessageMedia extends BaseMediaEntity {

}
