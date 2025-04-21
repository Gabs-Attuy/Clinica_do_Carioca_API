package com.tdm.clinica.dto.response;

import com.tdm.clinica.model.ConsultaModel;

public class HistoricoPacienteResponseDTO {
    private String medico;
    private String especialidade;
    private String dataConsulta;
    private String status;

    public HistoricoPacienteResponseDTO(ConsultaModel consulta) {
        this.medico = consulta.getMedico().getNome();
        this.especialidade = consulta.getMedico().getEspecialidade();
        this.dataConsulta = consulta.getDataConsulta().toString();
        this.status = consulta.getStatusConsulta().getDescricao();
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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
