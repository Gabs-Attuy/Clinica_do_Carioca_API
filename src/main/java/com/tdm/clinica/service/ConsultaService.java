package com.tdm.clinica.service;

import com.tdm.clinica.dto.ConsultaDTO;
import com.tdm.clinica.dto.ConsultaResponseDTO;
import com.tdm.clinica.dto.ErrorResponseDTO;
import com.tdm.clinica.dto.HistoricoPacienteResponseDTO;
import com.tdm.clinica.model.ConsultaModel;
import com.tdm.clinica.model.MedicoModel;
import com.tdm.clinica.model.PacienteModel;
import com.tdm.clinica.model.StatusConsultaModel;
import com.tdm.clinica.repository.ConsultaRepository;
import com.tdm.clinica.repository.MedicoRepository;
import com.tdm.clinica.repository.PacienteRepository;
import com.tdm.clinica.repository.StatusConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public ResponseEntity<?> listarHistoricoConsultasPaciente(Long pacienteId, int page, int size) {
        if (pacienteId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça o id do paciente!"));
        }
        PacienteModel pacienteModel = pacienteRepository.findById(pacienteId).orElse(null);
        if (pacienteModel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Paciente não encontrado no sistema!"));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<ConsultaModel> consultaPage = consultaRepository.findAllByPaciente(pageable, pacienteModel);
        if (consultaPage.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("Não foram encontradas consultas para esse paciente!"));
        }
        List<HistoricoPacienteResponseDTO> consultas = consultaPage.stream()
                .map(HistoricoPacienteResponseDTO::new)
                .collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("consultas", consultas);
        response.put("totalPages", consultaPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
