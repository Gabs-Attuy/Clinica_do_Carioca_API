package com.tdm.clinica.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AlteracaoMedicoRequestDTO(
        @NotNull Long id,
        String nome,
        String crm,
        String especialidade,
        String telefone,
        @Email String email,
        @Size(min = 8, max = 24, message = "Sua nova senha deve conter entre 8 e 24 caracteres.") String senha
) { }
