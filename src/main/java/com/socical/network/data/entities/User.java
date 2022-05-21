package com.socical.network.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "last_online", columnDefinition = "timestamp with time zone")
    @Schema(hidden = true)
    private LocalDateTime lastOnline;

    @Column(name = "online")
    @Schema(hidden = true)
    private boolean online = false;

    @Column(name = "active")
    @Schema(hidden = true)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "media_id")
    @Schema(hidden = true)
    private Media avatar;

    private String address;

    private String phone;

    @ManyToMany
    @JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
