package com.tdm.clinica.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record PacienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataNascimento
) { }

