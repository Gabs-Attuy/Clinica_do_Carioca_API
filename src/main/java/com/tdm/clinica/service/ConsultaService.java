package com.tdm.clinica.service;

import com.tdm.clinica.dto.ConsultaDTO;
import com.tdm.clinica.dto.ConsultaResponseDTO;
import com.tdm.clinica.model.ConsultaModel;
import com.tdm.clinica.model.MedicoModel;
import com.tdm.clinica.model.PacienteModel;
import com.tdm.clinica.model.StatusConsultaModel;
import com.tdm.clinica.repository.ConsultaRepository;
import com.tdm.clinica.repository.MedicoRepository;
import com.tdm.clinica.repository.PacienteRepository;
import com.tdm.clinica.repository.StatusConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private StatusConsultaRepository statusConsultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public ResponseEntity<ConsultaResponseDTO> agendarConsulta(ConsultaDTO dto) {

        MedicoModel medico = medicoRepository.findById(dto.getMedicoId().getId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        PacienteModel paciente = pacienteRepository.findById(dto.getPacienteId().getId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        StatusConsultaModel statusConsulta = new StatusConsultaModel(dto);
        statusConsultaRepository.save(statusConsulta);

        ConsultaModel novaConsulta = new ConsultaModel(dto);
        novaConsulta.setStatusConsultaModel(statusConsulta);
        novaConsulta.setMedicoModel(medico);
        novaConsulta.setPacienteModel(paciente);
        consultaRepository.save(novaConsulta);

        ConsultaResponseDTO response = new ConsultaResponseDTO(dto);
        response.setStatus(statusConsulta.getDescricao());
        response.setMedico(medico.getNome());

        return ResponseEntity.status(201).body(response);
    }
}
