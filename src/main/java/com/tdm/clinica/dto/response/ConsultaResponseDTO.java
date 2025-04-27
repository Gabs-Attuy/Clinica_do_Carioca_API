package com.tdm.clinica.dto.response;

import com.tdm.clinica.dto.request.ConsultaDTO;

import java.util.Date;

public class ConsultaResponseDTO {

    private Date dataConsulta;
    private String unidade;
    private String modalidade;
    private String observacoes;
    private String status;
    private String medico;

    public ConsultaResponseDTO() {
    }

    public ConsultaResponseDTO(ConsultaDTO dto) {
        this.dataConsulta = dto.getDataConsulta();
        this.unidade = dto.getUnidade();
        this.modalidade = dto.getModalidade();
        this.observacoes = dto.getObservacoes();
    }

    public String getMedico() {
        return medico;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getModalidade() {
        return modalidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
}
