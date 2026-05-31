package com.fut_sexta.fut_sexta.repository;

import com.fut_sexta.fut_sexta.DTO.output.ArtilheiroDTO;
import com.fut_sexta.fut_sexta.DTO.output.MatchGoalDetailOutput;
import com.fut_sexta.fut_sexta.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    @Query("""
    SELECT new com.fut_sexta.fut_sexta.DTO.output.ArtilheiroDTO(p.name, COUNT(g))
    FROM Goal g
    JOIN g.player p
    WHERE g.user.id = :userId
    GROUP BY p.name
    ORDER BY COUNT(g) DESC
""")
    List<ArtilheiroDTO> findArtilheiros(Long userId);

    Optional<Goal> findByIdAndUserId(Long id,Long userId);

    @Query(
            """
                    SELECT new com.fut_sexta.fut_sexta.DTO.output.MatchGoalDetailOutput(p.name, COUNT(g), g.teamSide)
                    FROM Goal g
                    JOIN g.player p
                    WHERE g.user.id = :userId AND g.match.id = :id
                    GROUP BY p.name, g.teamSide
                    """
    )
    List<MatchGoalDetailOutput> findMatchGoalDetails(Long id, Long userId);
}
