package com.tdm.clinica.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

public record CadastroPacienteRequestDTO(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull Date dataNascimento,
        @NotBlank @CPF String cpf,
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, max = 24, message = "Sua senha deve conter entre 8 e 24 caracteres.") String senha
) { }
