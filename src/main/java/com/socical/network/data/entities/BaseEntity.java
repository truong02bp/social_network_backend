package com.socical.network.data.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Schema(hidden = true)
    private String createdBy;

    @LastModifiedBy
    @Schema(hidden = true)
    private String lastModifiedBy;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "timestamp with time zone")
    @Schema(hidden = true)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "timestamp with time zone")
    @Schema(hidden = true)
    private LocalDateTime lastModifiedDate;
}
