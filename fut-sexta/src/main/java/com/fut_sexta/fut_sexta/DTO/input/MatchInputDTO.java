package com.fut_sexta.fut_sexta.DTO.input;

import jakarta.validation.constraints.NotBlank;

public record MatchInputDTO(@NotBlank(message = "Id obrigatório") Long teamAId,
                            @NotBlank(message = "Id obrigatório") Long teamBId,
                            @NotBlank(message = "Tempo obrigatório") int minutos) {
}
