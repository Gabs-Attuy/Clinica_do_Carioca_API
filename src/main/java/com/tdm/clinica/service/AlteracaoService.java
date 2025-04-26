package com.tdm.clinica.service;

import com.tdm.clinica.dto.request.AlteracaoMedicoRequestDTO;
import com.tdm.clinica.dto.request.AlteracaoPacienteRequestDTO;
import com.tdm.clinica.dto.response.AlteracaoMedicoResponseDTO;
import com.tdm.clinica.dto.response.AlteracaoPacienteResponseDTO;
import com.tdm.clinica.model.CredencialModel;
import com.tdm.clinica.model.MedicoModel;
import com.tdm.clinica.model.PacienteModel;
import com.tdm.clinica.repository.CredencialRepository;
import com.tdm.clinica.repository.MedicoRepository;
import com.tdm.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AlteracaoService {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public AlteracaoPacienteResponseDTO alteracaoPaciente (AlteracaoPacienteRequestDTO dto) {
        PacienteModel pacienteModel = pacienteRepository.findById(dto.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        Optional.ofNullable(dto.nome()).ifPresent(pacienteModel::setNome);
        Optional.ofNullable(dto.telefone()).ifPresent(pacienteModel::setTelefone);

        if (dto.email() != null || dto.senha() != null) {
            CredencialModel credencial = pacienteModel.getCredencialModel();

            Optional.ofNullable(dto.email()).ifPresent(credencial::setEmail);

            Optional.ofNullable(dto.senha()).ifPresent(senha -> {
                credencial.setSenha(bCryptPasswordEncoder.encode(senha));
            });
        }

        pacienteRepository.save(pacienteModel);

        return new AlteracaoPacienteResponseDTO(
               pacienteModel.getNome(),
               pacienteModel.getTelefone(),
               pacienteModel.getCredencialModel().getEmail()
        );
    }

    @Transactional
    public AlteracaoMedicoResponseDTO alteracaoMedico (AlteracaoMedicoRequestDTO dto) {
        MedicoModel medicoModel = medicoRepository.findById(dto.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        Optional.ofNullable(dto.nome()).ifPresent(medicoModel::setNome);
        Optional.ofNullable(dto.especialidade()).ifPresent(medicoModel::setEspecialidade);
        Optional.ofNullable(dto.telefone()).ifPresent(medicoModel::setTelefone);

        if (dto.email() != null || dto.senha() != null) {
            CredencialModel credencial = medicoModel.getCredencialModel();

            Optional.ofNullable(dto.email()).ifPresent(credencial::setEmail);

            Optional.ofNullable(dto.senha()).ifPresent(senha -> {
                credencial.setSenha(bCryptPasswordEncoder.encode(senha));
            });
        }

        medicoRepository.save(medicoModel);

        return new AlteracaoMedicoResponseDTO(
                medicoModel.getNome(),
                medicoModel.getEspecialidade(),
                medicoModel.getTelefone(),
                medicoModel.getCredencialModel().getEmail()
        );
    }
}
