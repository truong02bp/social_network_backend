package com.socical.network.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "media")
@Getter
@Setter
public class Media extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "url", unique = true, nullable = false)
    private String url;

}
