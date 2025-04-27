package com.tdm.clinica.repository;

import com.tdm.clinica.model.CredencialModel;
import com.tdm.clinica.model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
    Optional<MedicoModel> findByCredencialModel(CredencialModel credencial);

    MedicoModel findByEspecialidade(String especialidade);
}
