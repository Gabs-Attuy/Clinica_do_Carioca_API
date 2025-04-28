package com.tdm.clinica.service;

import com.tdm.clinica.dto.response.MedicoResponseDTO;
import com.tdm.clinica.model.CredencialModel;
import com.tdm.clinica.model.MedicoModel;
import com.tdm.clinica.repository.CredencialRepository;
import com.tdm.clinica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private CredencialRepository credencialRepository;

    public MedicoResponseDTO getMedicoById(Long id) {
        CredencialModel credencial = credencialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credencial not found"));
        MedicoModel medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medico not found"));
        return new MedicoResponseDTO(
                medico.getId(),
                medico.getNome(),
                medico.getEspecialidade(),
                medico.getTelefone(),
                credencial.getEmail(),
                medico.getCrm()
        );
    }
}