package com.socical.network.data.repositories;

import com.socical.network.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE User SET name = ?1, username = ?2, address = ?3, phone = ?4 where id = ?5 ")
    int update(String name, String username, String address, String phone, Long userId);
}
