package com.tdm.clinica.controller;

import com.tdm.clinica.dto.ConsultaDTO;
import com.tdm.clinica.dto.ConsultaResponseDTO;
import com.tdm.clinica.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
