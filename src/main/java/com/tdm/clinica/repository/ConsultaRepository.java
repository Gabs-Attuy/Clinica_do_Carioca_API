package com.tdm.clinica.repository;

import com.tdm.clinica.model.ConsultaModel;
import com.tdm.clinica.model.PacienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
    Page<ConsultaModel> findAllByPaciente(Pageable pageable, PacienteModel pacienteId);
}
