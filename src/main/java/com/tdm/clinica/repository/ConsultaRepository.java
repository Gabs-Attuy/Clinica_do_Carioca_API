package com.tdm.clinica.repository;

import com.tdm.clinica.model.ConsultaModel;
import com.tdm.clinica.model.MedicoModel;
import com.tdm.clinica.model.PacienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
    Page<ConsultaModel> findAllByPaciente(Pageable pageable, PacienteModel pacienteId);

    Page<ConsultaModel> findAllByMedico(Pageable pageable, MedicoModel medicoModel);

    ConsultaModel findByPaciente(PacienteModel pacienteId);

    @Query("SELECT c FROM ConsultaModel c WHERE c.paciente.nome LIKE %?1% AND c.medico.id = ?2")
    Page<ConsultaModel> findAllByNomePacienteAndMedicoId(String nomePaciente, Long medicoId, Pageable pageable);

    @Query("SELECT c FROM ConsultaModel c WHERE c.medico.nome LIKE %?1% AND c.paciente.id = ?2")
    Page<ConsultaModel> findAllByNomeMedicoAndPacienteId(String nomeMedico, Long pacienteId, Pageable pageable);
}
