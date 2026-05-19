package com.fut_sexta.fut_sexta.DTO.input;

import com.fut_sexta.fut_sexta.model.TeamSide;
import jakarta.validation.constraints.NotBlank;

public record GoalDTO(@NotBlank(message = "MatchId obrigatório") Long matchId,
                      @NotBlank(message = "PlayerId obrigatório") Long playerId,
                      @NotBlank(message = "TeamSide obrigatório") TeamSide side) {
}
