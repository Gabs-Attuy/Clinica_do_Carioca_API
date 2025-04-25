package com.tdm.clinica.controller;

import com.tdm.clinica.dto.request.CadastroMedicoRequestDTO;
import com.tdm.clinica.dto.request.CadastroPacienteRequestDTO;
import com.tdm.clinica.dto.response.CadastroMedicoResponseDTO;
import com.tdm.clinica.dto.response.CadastroPacienteResponseDTO;
import com.tdm.clinica.service.CadastroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @PostMapping("/medico")
    public ResponseEntity<?> cadastroDeMedico(@RequestBody @Valid CadastroMedicoRequestDTO cadastroMedicoRequestDTO) {
        try {
            CadastroMedicoResponseDTO response = cadastroService.cadastroDeMedico(cadastroMedicoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException e) {
            String mensagemErro = e.getMessage();

            if (mensagemErro.contains("UK_medico_crm")) {
                mensagemErro = "Já existe um médico cadastrado com este CRM.";
            } else if (mensagemErro.contains("UK_medico_telefone")) {
                mensagemErro = "Já existe um usuário cadastrado com este telefone.";
            } else if (mensagemErro.contains("UK_email")) {
                mensagemErro = "Já existe um usuário cadastrado com este e-mail.";
            }

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(mensagemErro);
        }
    }

    @PostMapping("/paciente")
    public ResponseEntity<?> cadastroDePaciente(@RequestBody @Valid CadastroPacienteRequestDTO cadastroPacienteRequestDTO) {
        try {
            CadastroPacienteResponseDTO response = cadastroService.cadastroDePaciente(cadastroPacienteRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException e) {
            String mensagemErro = e.getMessage();

            if (mensagemErro.contains("UK_paciente_cpf")) {
                mensagemErro = "Já existe um paciente cadastrado com este CPF.";
            } else if (mensagemErro.contains("UK_paciente_telefone")) {
                mensagemErro = "Já existe um usuário cadastrado com este telefone.";
            } else if (mensagemErro.contains("UK_email")) {
                mensagemErro = "Já existe um usuário cadastrado com este e-mail.";
            }

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(mensagemErro);
        }
    }
}
