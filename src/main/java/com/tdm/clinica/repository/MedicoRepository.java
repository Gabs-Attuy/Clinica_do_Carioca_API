package com.tdm.clinica.repository;

import com.tdm.clinica.model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
}
