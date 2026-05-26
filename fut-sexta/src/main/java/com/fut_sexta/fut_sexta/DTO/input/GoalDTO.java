package com.fut_sexta.fut_sexta.DTO.input;

import com.fut_sexta.fut_sexta.model.TeamSide;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GoalDTO(@NotNull(message = "MatchId obrigatório") Long matchId,
                      @NotNull(message = "PlayerId obrigatório") Long playerId,
                      @NotNull(message = "TeamSide obrigatório") TeamSide side) {
}
