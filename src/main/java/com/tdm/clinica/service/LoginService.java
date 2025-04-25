package com.tdm.clinica.service;

import com.tdm.clinica.dto.request.LoginRequestDTO;
import com.tdm.clinica.dto.response.LoginResponseDTO;
import com.tdm.clinica.model.CredencialModel;
import com.tdm.clinica.model.MedicoModel;
import com.tdm.clinica.model.PacienteModel;
import com.tdm.clinica.repository.CredencialRepository;
import com.tdm.clinica.repository.MedicoRepository;
import com.tdm.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        CredencialModel credencial = credencialRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado."));

        if (!bCryptPasswordEncoder.matches(loginRequestDTO.password(), credencial.getSenha())) {
            throw new BadCredentialsException("Senha inválida.");
        }

        String perfil = credencial.getPerfil();

        if (perfil.equals("MEDICO")) {
            MedicoModel medico = medicoRepository.findByCredencialModel(credencial)
                    .orElseThrow(() -> new IllegalStateException("Médico não encontrado."));
            return new LoginResponseDTO(medico.getId(), medico.getNome(), perfil);
        } else if (perfil.equals("PACIENTE")) {
            PacienteModel paciente = pacienteRepository.findByCredencialModel(credencial)
                    .orElseThrow(() -> new IllegalStateException("Paciente não encontrado."));
            return new LoginResponseDTO(paciente.getId(), paciente.getNome(), perfil);
        }

        throw new IllegalStateException("Perfil inválido.");
    }
}
