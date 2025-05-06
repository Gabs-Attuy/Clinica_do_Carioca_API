package com.tdm.clinica.dto.response;

public record MedicoResponseDTO(
        Long id,
        String name,
        String especialidade,
        String telefone,
        String email,
        String crm
) { }