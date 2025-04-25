package com.tdm.clinica.dto.response;

public record CadastroMedicoResponseDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String especialidade,
        String telefone
) { }