package com.fut_sexta.fut_sexta.repository;

import com.fut_sexta.fut_sexta.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByNameAndUserId(String name, Long userId);

    List<Player> findByUserId(Long userId);

    Optional<Player> findByIdAndUserId(Long id, Long userId);
}
