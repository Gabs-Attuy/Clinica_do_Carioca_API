package com.tdm.clinica.dto.response;

import com.tdm.clinica.model.ConsultaModel;

public class HistoricoMedicoResponseDTO {
    private String paciente;
    private String modalidade;
    private String dataConsulta;
    private String status;

    public HistoricoMedicoResponseDTO(ConsultaModel consulta) {
        this.paciente = consulta.getPaciente().getNome();
        this.modalidade = consulta.getModalidade();
        this.dataConsulta = consulta.getDataConsulta().toString();
        this.status = consulta.getStatusConsulta().getDescricao();
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
