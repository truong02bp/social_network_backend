package com.socical.network.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "media")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Media extends BaseMediaEntity {

}
