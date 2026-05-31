package com.fut_sexta.fut_sexta.DTO.output;

import com.fut_sexta.fut_sexta.model.TeamSide;

public record MatchGoalDetailOutput(String name, Long goals, TeamSide teamSide) {
}
