package com.tdm.clinica.controller;

import com.tdm.clinica.dto.response.MedicoResponseDTO;
import com.tdm.clinica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
@CrossOrigin(origins = "*")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/{id}")
    public MedicoResponseDTO getMedicoById(@PathVariable Long id) {
        return medicoService.getMedicoById(id);
    }
}