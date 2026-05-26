package com.fut_sexta.fut_sexta.repository;

import com.fut_sexta.fut_sexta.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByNameAndUserId(String name, Long userId);

    Optional<Team> findByIdAndUserId(Long id, Long userId);

    List<Team> findByUserId(Long userId);
}
