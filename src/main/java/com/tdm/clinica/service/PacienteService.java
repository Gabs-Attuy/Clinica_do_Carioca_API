package com.tdm.clinica.service;

import com.tdm.clinica.dto.response.PacienteResponseDTO;
import com.tdm.clinica.model.CredencialModel;
import com.tdm.clinica.model.PacienteModel;
import com.tdm.clinica.repository.CredencialRepository;
import com.tdm.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CredencialRepository credencialRepository;

    public PacienteResponseDTO getPacienteById(Long id) {
        PacienteModel paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente not found"));
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getCredencialModel().getEmail(),
                paciente.getTelefone(),
                paciente.getDataNascimento()
        );
    }
}
