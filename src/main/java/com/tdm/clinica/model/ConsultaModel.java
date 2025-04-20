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

    public ConsultaModel() {
    }

    public ConsultaModel(Long id, String unidade, String observacaoPaciente, Date dataConsulta, String modalidade, PacienteModel pacienteModel, MedicoModel medicoModel, StatusConsultaModel statusConsultaModel) {
        this.id = id;
        this.unidade = unidade;
        this.observacaoPaciente = observacaoPaciente;
        DataConsulta = dataConsulta;
        this.modalidade = modalidade;
        this.pacienteModel = pacienteModel;
        this.medicoModel = medicoModel;
        this.statusConsultaModel = statusConsultaModel;
    }

    public ConsultaModel(ConsultaDTO dto) {
        this.modalidade = dto.getModalidade();
        this.unidade = dto.getUnidade();
        this.medicoModel = dto.getMedicoId();
        this.DataConsulta = dto.getDataConsulta();
        this.pacienteModel = dto.getPacienteId();
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
        return DataConsulta;
    }

    public String getModalidade() {
        return modalidade;
    }

    public PacienteModel getPacienteModel() {
        return pacienteModel;
    }

    public MedicoModel getMedicoModel() {
        return medicoModel;
    }

    public StatusConsultaModel getStatusConsultaModel() {
        return statusConsultaModel;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setObservacaoPaciente(String observacaoPaciente) {
        this.observacaoPaciente = observacaoPaciente;
    }

    public void setDataConsulta(Date dataConsulta) {
        DataConsulta = dataConsulta;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public void setPacienteModel(PacienteModel pacienteModel) {
        this.pacienteModel = pacienteModel;
    }

    public void setMedicoModel(MedicoModel medicoModel) {
        this.medicoModel = medicoModel;
    }

    public void setStatusConsultaModel(StatusConsultaModel statusConsultaModel) {
        this.statusConsultaModel = statusConsultaModel;
    }
}
