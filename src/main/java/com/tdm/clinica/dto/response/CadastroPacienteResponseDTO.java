package com.tdm.clinica.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record CadastroPacienteResponseDTO(
        Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataNascimento,
        String cpf,
        String nome,
        String email,
        String telefone
) { }
