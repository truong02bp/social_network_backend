package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "post_media")
@Getter
@Setter
public class PostMedia extends BaseMediaEntity {

}
