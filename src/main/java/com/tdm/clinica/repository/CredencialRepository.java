package com.tdm.clinica.repository;

import com.tdm.clinica.model.CredencialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CredencialRepository extends JpaRepository<CredencialModel, Long> {
    Optional<CredencialModel> findByEmail(@Param("email") String email);
}
