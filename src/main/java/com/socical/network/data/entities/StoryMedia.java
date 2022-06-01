package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "story_media")
@Getter
@Setter
public class StoryMedia extends BaseMediaEntity {
}
