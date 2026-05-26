package com.fut_sexta.fut_sexta.repository;

import com.fut_sexta.fut_sexta.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByFinishedTrueAndUserId(Long userId);

    Optional<Match> findByIdAndUserId(Long id, Long userId);
}
