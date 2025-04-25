package com.tdm.clinica.service;

import com.tdm.clinica.dto.request.CadastroMedicoRequestDTO;
import com.tdm.clinica.dto.request.CadastroPacienteRequestDTO;
import com.tdm.clinica.dto.response.CadastroMedicoResponseDTO;
import com.tdm.clinica.dto.response.CadastroPacienteResponseDTO;
import com.tdm.clinica.model.CredencialModel;
import com.tdm.clinica.model.MedicoModel;
import com.tdm.clinica.model.PacienteModel;
import com.tdm.clinica.repository.CredencialRepository;
import com.tdm.clinica.repository.MedicoRepository;
import com.tdm.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class CadastroService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CredencialRepository credencialRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public CadastroMedicoResponseDTO cadastroDeMedico(CadastroMedicoRequestDTO cadastroMedicoRequestDTO) {
        CredencialModel credencialModel = new CredencialModel();
        credencialModel.setEmail(cadastroMedicoRequestDTO.email());
        credencialModel.setSenha(bCryptPasswordEncoder.encode(cadastroMedicoRequestDTO.senha()));
        credencialModel.setPerfil("MEDICO");
        credencialRepository.save(credencialModel);

        MedicoModel medicoModel = new MedicoModel();
        medicoModel.setCrm(cadastroMedicoRequestDTO.crm());
        medicoModel.setEspecialidade(cadastroMedicoRequestDTO.especialidade());
        medicoModel.setNome(cadastroMedicoRequestDTO.nome());
        medicoModel.setTelefone(cadastroMedicoRequestDTO.telefone());
        medicoModel.setCredencialModel(credencialModel);
        medicoRepository.save(medicoModel);

        return new CadastroMedicoResponseDTO(
                medicoModel.getId(),
                medicoModel.getNome(),
                credencialModel.getEmail(),
                medicoModel.getCrm(),
                medicoModel.getEspecialidade(),
                medicoModel.getTelefone()
        );
    }

    @Transactional
    public CadastroPacienteResponseDTO cadastroDePaciente(CadastroPacienteRequestDTO cadastroPacienteRequestDTO) {
        CredencialModel credencialModel = new CredencialModel();
        credencialModel.setEmail(cadastroPacienteRequestDTO.email());
        credencialModel.setSenha(bCryptPasswordEncoder.encode(cadastroPacienteRequestDTO.senha()));
        credencialModel.setPerfil("PACIENTE");
        credencialRepository.save(credencialModel);

        PacienteModel pacienteModel = new PacienteModel();
        pacienteModel.setDataNascimento(cadastroPacienteRequestDTO.dataNascimento());
        pacienteModel.setCpf(cadastroPacienteRequestDTO.cpf());
        pacienteModel.setNome(cadastroPacienteRequestDTO.nome());
        pacienteModel.setTelefone(cadastroPacienteRequestDTO.telefone());
        pacienteModel.setCredencialModel(credencialModel);
        pacienteRepository.save(pacienteModel);

        return new CadastroPacienteResponseDTO(
                pacienteModel.getId(),
                pacienteModel.getDataNascimento(),
                pacienteModel.getCpf(),
                pacienteModel.getNome(),
                credencialModel.getEmail(),
                pacienteModel.getTelefone()
        );
    }
}
