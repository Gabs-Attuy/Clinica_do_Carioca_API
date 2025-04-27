package com.tdm.clinica.dto.request;

import com.tdm.clinica.model.MedicoModel;
import com.tdm.clinica.model.PacienteModel;

import java.util.Date;

public class ConsultaDTO {

    private String unidade;
    private PacienteModel pacienteId;
    private String modalidade;
    private Date dataConsulta;
    private String observacoes;
    private MedicoModel medicoId;

    public ConsultaDTO() {
    }

    public ConsultaDTO(String unidade, PacienteModel pacienteId, String modalidade, Date dataConsulta, String observacoes, MedicoModel medicoId) {
        this.unidade = unidade;
        this.pacienteId = pacienteId;
        this.modalidade = modalidade;
        this.dataConsulta = dataConsulta;
        this.observacoes = observacoes;
        this.medicoId = medicoId;
    }

    public String getUnidade() {
        return unidade;
    }

    public PacienteModel getPacienteId() {
        return pacienteId;
    }

    public String getModalidade() {
        return modalidade;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public MedicoModel getMedicoId() {
        return medicoId;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setPacienteId(PacienteModel pacienteId) {
        this.pacienteId = pacienteId;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setMedicoId(MedicoModel medicoId) {
        this.medicoId = medicoId;
    }
}
