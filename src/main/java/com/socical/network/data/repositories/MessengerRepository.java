package com.socical.network.data.repositories;

import com.socical.network.data.entities.Messenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessengerRepository extends JpaRepository<Messenger, Long> {
}
