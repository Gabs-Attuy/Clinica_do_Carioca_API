package com.tdm.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdm.clinica.dto.ConsultaDTO;
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
    private Date dataConsulta;
    private String modalidade;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteModel paciente;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private MedicoModel medico;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "status_id")
    private StatusConsultaModel statusConsulta;

    public ConsultaModel() {
    }

    public ConsultaModel(Long id, String unidade, String observacaoPaciente, Date dataConsulta, String modalidade, PacienteModel pacienteModel, MedicoModel medicoModel, StatusConsultaModel statusConsultaModel) {
        this.id = id;
        this.unidade = unidade;
        this.observacaoPaciente = observacaoPaciente;
        this.dataConsulta = dataConsulta;
        this.modalidade = modalidade;
        this.paciente = pacienteModel;
        this.medico = medicoModel;
        this.statusConsulta = statusConsultaModel;
    }

    public ConsultaModel(ConsultaDTO dto) {
        this.modalidade = dto.getModalidade();
        this.unidade = dto.getUnidade();
        this.medico = dto.getMedicoId();
        this.dataConsulta = dto.getDataConsulta();
        this.paciente = dto.getPacienteId();
        this.observacaoPaciente = dto.getObservacoes();
    }

    public Long getId() {
        return id;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getObservacaoPaciente() {
        return observacaoPaciente;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public String getModalidade() {
        return modalidade;
    }

    public PacienteModel getPacienteModel() {
        return paciente;
    }

    public MedicoModel getMedicoModel() {
        return medico;
    }

    public StatusConsultaModel getStatusConsultaModel() {
        return statusConsulta;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setObservacaoPaciente(String observacaoPaciente) {
        this.observacaoPaciente = observacaoPaciente;
    }

    public void setDataConsulta(Date dataConsulta) {
        dataConsulta = dataConsulta;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public void setPacienteModel(PacienteModel pacienteModel) {
        this.paciente = pacienteModel;
    }

    public void setMedicoModel(MedicoModel medicoModel) {
        this.medico = medicoModel;
    }

    public void setStatusConsultaModel(StatusConsultaModel statusConsultaModel) {
        this.statusConsulta = statusConsultaModel;
    }
}
