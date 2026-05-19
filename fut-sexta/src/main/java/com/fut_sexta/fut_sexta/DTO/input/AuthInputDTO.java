package com.fut_sexta.fut_sexta.DTO.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthInputDTO(@NotBlank(message = "Username obrigatório")
                           @Size(min = 4, max = 20, message = "Username entre 4 e 20 caracteres") String username,
                           @NotBlank(message = "Password obrigatória")String password) {
}
