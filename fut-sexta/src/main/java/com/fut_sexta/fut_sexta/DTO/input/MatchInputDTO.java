package com.fut_sexta.fut_sexta.DTO.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MatchInputDTO(@NotNull(message = "Id obrigatório") Long teamAId,
                            @NotNull(message = "Id obrigatório") Long teamBId,
                            @NotNull(message = "Tempo obrigatório") int minutos) {
}
