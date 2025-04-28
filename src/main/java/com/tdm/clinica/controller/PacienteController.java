package com.tdm.clinica.controller;

import com.tdm.clinica.dto.response.PacienteResponseDTO;
import com.tdm.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    public PacienteResponseDTO getPacienteById(@PathVariable Long id) {
        return pacienteService.getPacienteById(id);
    }
}