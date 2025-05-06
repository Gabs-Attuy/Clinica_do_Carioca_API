package com.tdm.clinica.service;

import com.tdm.clinica.dto.request.ConsultaDTO;
import com.tdm.clinica.dto.response.*;
import com.tdm.clinica.dto.ErrorResponseDTO;
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

import java.util.Collections;
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
        novaConsulta.setStatusConsulta(statusConsulta);
        novaConsulta.setMedico(medico);
        novaConsulta.setPaciente(paciente);
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
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataConsulta"));
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

    public ResponseEntity<?> listarMedicoPorNome(String nomeMedico, Long pacienteId, int page, int size) {
        if (nomeMedico == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça o nome do médico!"));
        }
        if (pacienteId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça o id do paciente!"));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataConsulta"));
        Page<ConsultaModel> consultaPage = consultaRepository.findAllByNomeMedicoAndPacienteId(nomeMedico, pacienteId, pageable);
        if (consultaPage.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("Não foram encontradas consultas para essa pesquisa!"));
        }
        List<HistoricoPacienteResponseDTO> consultas = consultaPage.stream()
                .map(HistoricoPacienteResponseDTO::new)
                .collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("consultas", consultas);
        response.put("totalPages", consultaPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> listarHistoricoConsultasMedico(Long medicoId, int page, int size) {
        if (medicoId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça o id do médico!"));
        }
        MedicoModel medicoModel = medicoRepository.findById(medicoId).orElse(null);
        if (medicoModel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Médico não encontrado no sistema!"));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataConsulta"));
        Page<ConsultaModel> consultaPage = consultaRepository.findAllByMedico(pageable, medicoModel);
        if (consultaPage.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("Não foram encontradas consultas para esse médico!"));
        }
        List<HistoricoMedicoResponseDTO> consultas = consultaPage.stream()
                .map(HistoricoMedicoResponseDTO::new)
                .collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("consultas", consultas);
        response.put("totalPages", consultaPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> listarPacientePorNome(String nomePaciente, Long medicoId, int page, int size) {
        if (nomePaciente == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça o nome do paciente!"));
        }
        if (medicoId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça o id do médico!"));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataConsulta"));
        Page<ConsultaModel> consultaPage = consultaRepository.findAllByNomePacienteAndMedicoId(nomePaciente, medicoId, pageable);
        if (consultaPage.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("Não foram encontradas consultas para essa pesquisa!"));
        }
        List<HistoricoMedicoResponseDTO> consultas = consultaPage.stream()
                .map(HistoricoMedicoResponseDTO::new)
                .collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("consultas", consultas);
        response.put("totalPages", consultaPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> detalheConsulta(Long consultaId) {
        if (consultaId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça o id da consulta!"));
        }
        ConsultaModel consultaModel = consultaRepository.findById(consultaId).orElse(null);
        if (consultaModel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Consulta não encontrada no sistema!"));
        }
        DetalheConsultaPacienteResponseDTO detalheConsulta = new DetalheConsultaPacienteResponseDTO(consultaModel);
        return new ResponseEntity<>(detalheConsulta, HttpStatus.OK);
    }

    public ResponseEntity<?> cancelarConsulta(Long consultaId) {
        if (consultaId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça o id da consulta!"));
        }
        ConsultaModel consultaModel = consultaRepository.findById(consultaId).orElse(null);
        if (consultaModel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Consulta não encontrada no sistema!"));
        }
        consultaModel.getStatusConsulta().setDescricao("Cancelada");
        consultaRepository.save(consultaModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> observacoesMedico(Long consultaId, String obs, String novoStatus) {
        ConsultaModel consulta = consultaRepository.findById(consultaId).orElse(null);

        StatusConsultaModel statusConsulta = consulta.getStatusConsulta();
        if (statusConsulta == null) {
            throw new RuntimeException("Status da consulta não encontrado!");
        }

        statusConsulta.setObservacaoMedico(obs.replace("\"", ""));
        statusConsulta.setDescricao(novoStatus.replace("\"", ""));
        statusConsultaRepository.save(statusConsulta);

        return ResponseEntity.ok("Observação adicionada com sucesso!\n" + obs);

    }

    public ResponseEntity<List<ListagemMedicoResponseDTO>> listarMedicoPorEspecialidade(String especialidade) {
        List<MedicoModel> medicos = medicoRepository.findByEspecialidade(especialidade);
        if (medicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        List<ListagemMedicoResponseDTO> listagem = medicos.stream()
                .map(ListagemMedicoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listagem);
    }

}
