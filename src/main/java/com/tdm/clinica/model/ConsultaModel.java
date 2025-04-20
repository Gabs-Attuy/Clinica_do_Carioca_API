package com.tdm.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "Consulta")
public class ConsultaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unidade;
    @Column(columnDefinition = "TEXT")
    private String observacaoPaciente;
    private Date DataConsulta;
    private String modalidade;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteModel pacienteModel;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private MedicoModel medicoModel;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "status_id")
    private StatusConsultaModel statusConsultaModel;
}
