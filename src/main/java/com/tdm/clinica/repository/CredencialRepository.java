package com.tdm.clinica.repository;

import com.tdm.clinica.model.CredencialModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialRepository extends JpaRepository<CredencialModel, Long> {
}
