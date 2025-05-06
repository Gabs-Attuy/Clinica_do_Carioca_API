package com.tdm.clinica.controller;

import com.tdm.clinica.dto.request.AlteracaoMedicoRequestDTO;
import com.tdm.clinica.dto.request.AlteracaoPacienteRequestDTO;
import com.tdm.clinica.dto.response.AlteracaoMedicoResponseDTO;
import com.tdm.clinica.dto.response.AlteracaoPacienteResponseDTO;
import com.tdm.clinica.service.AlteracaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alteracao")
@CrossOrigin(origins = "*")
public class AlteracaoController {

    @Autowired
    private AlteracaoService alteracaoService;

    @PatchMapping("/paciente")
    public ResponseEntity<?> alteracaoPaciente(@RequestBody @Valid AlteracaoPacienteRequestDTO dto) {
        try {
            AlteracaoPacienteResponseDTO response = alteracaoService.alteracaoPaciente(dto);
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            String mensagemErro = e.getMessage();

            if (mensagemErro.contains("UK_email"))
                mensagemErro = "Este e-mail já está em uso.";

            else if (mensagemErro.contains("UK_paciente_telefone"))
                mensagemErro = "Este telefone já está em uso.";

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(mensagemErro);
        }
    }

    @PatchMapping("/medico")
    public ResponseEntity<?> alteracaoMedico(@RequestBody @Valid AlteracaoMedicoRequestDTO dto) {
        try {
            AlteracaoMedicoResponseDTO response = alteracaoService.alteracaoMedico(dto);
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            String mensagemErro = e.getMessage();

            if(mensagemErro.contains("UK_email"))
                mensagemErro = "Este e-mail já está em uso.";

            else if (mensagemErro.contains("UK_medico_telefone"))
                mensagemErro = "Este telefone já está em uso.";

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(mensagemErro);
        }
    }
}
