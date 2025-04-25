package com.tdm.clinica.repository;

import com.tdm.clinica.model.CredencialModel;
import com.tdm.clinica.model.PacienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {
    Page<PacienteModel> findAllByNome(String nome, Pageable pageable);
    Optional<PacienteModel> findByCredencialModel(CredencialModel credencial);
}
