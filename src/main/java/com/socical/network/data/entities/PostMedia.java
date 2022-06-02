package com.socical.network.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "post_media")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostMedia extends BaseMediaEntity {

}
