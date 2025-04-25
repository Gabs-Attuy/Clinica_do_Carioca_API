package com.tdm.clinica.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroMedicoRequestDTO(
        @NotBlank String crm,
        @NotBlank String especialidade,
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, max = 24, message = "Sua senha deve conter entre 8 e 24 caracteres.") String senha
) { }