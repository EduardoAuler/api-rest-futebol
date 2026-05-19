package com.fut_sexta.fut_sexta.DTO.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlayerTeamInputDTO(@NotBlank(message = "Nome obrigatório")
                                 @Size(max = 20, message = "Nome com no máximo 20 caracteres") String name) {
}
