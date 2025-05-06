package com.tdm.clinica.controller;

import com.tdm.clinica.dto.request.ConsultaDTO;
import com.tdm.clinica.dto.response.ConsultaResponseDTO;
import com.tdm.clinica.dto.response.ListagemMedicoResponseDTO;
import com.tdm.clinica.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
@CrossOrigin("*")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/agendamento")
    public ResponseEntity<ConsultaResponseDTO> agendarConsulta(@RequestBody ConsultaDTO dto) {
        return consultaService.agendarConsulta(dto);
    }

    @PatchMapping("/cancelar")
    public ResponseEntity<?> cancelarConsulta(@RequestParam("consultaId") Long consultaId) {
        return consultaService.cancelarConsulta(consultaId);
    }

    @GetMapping("/historico/paciente")
    public ResponseEntity<?> listarHistoricoConsultasPaciente(
            @RequestParam("pacienteId") Long pacienteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return consultaService.listarHistoricoConsultasPaciente(pacienteId, page, size);
    }

    @GetMapping("/historico/paciente/nomeMedico")
    public ResponseEntity<?> listarMedicoPorNome(
            @RequestParam("nomeMedico") String nomeMedico,
            @RequestParam("pacienteId") Long pacienteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return consultaService.listarMedicoPorNome(nomeMedico, pacienteId, page, size);
    }

    @GetMapping("/historico/medico")
    public ResponseEntity<?> listarHistoricoConsultasMedico(
            @RequestParam("medicoId") Long medicoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return consultaService.listarHistoricoConsultasMedico(medicoId, page, size);
    }

    @GetMapping("/historico/medico/nomePaciente")
    public ResponseEntity<?> listarPacientePorNome(
            @RequestParam("nomePaciente") String nomePaciente,
            @RequestParam("medicoId") Long medicoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return consultaService.listarPacientePorNome(nomePaciente, medicoId, page, size);
    }

    @GetMapping("/detalhe")
    public ResponseEntity<?> detalheConsultaPaciente(
            @RequestParam("consultaId") Long consultaId) {
        return consultaService.detalheConsulta(consultaId);
    }

    @PostMapping("/add/observacao")
    public ResponseEntity<?> observacoesMedico(@RequestParam ("consultaId") Long consultaId,
                                               @RequestBody String obs, String novoStatus) {
        return consultaService.observacoesMedico(consultaId, obs, novoStatus);
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<ListagemMedicoResponseDTO>> listagemMedicos(@RequestParam ("especialidade")
                                                                               String especialidade) {
        return consultaService.listarMedicoPorEspecialidade(especialidade);
    }
}
