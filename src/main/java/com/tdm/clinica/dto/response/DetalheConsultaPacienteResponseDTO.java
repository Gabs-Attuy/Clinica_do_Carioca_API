package com.tdm.clinica.dto.response;

import com.tdm.clinica.model.ConsultaModel;

public class DetalheConsultaPacienteResponseDTO {
    private String unidade;
    private String observacaoPaciente;
    private String observacaoMedico;
    private String dataConsulta;
    private String modalidade;
    private String paciente;
    private String medico;
    private String statusConsulta;

    public DetalheConsultaPacienteResponseDTO(ConsultaModel consultaModel) {
        this.unidade = consultaModel.getUnidade();
        this.observacaoPaciente = consultaModel.getObservacaoPaciente();
        this.observacaoMedico = consultaModel.getStatusConsulta().getObservacaoMedico();
        this.dataConsulta = consultaModel.getDataConsulta().toString();
        this.modalidade = consultaModel.getModalidade();
        this.paciente = consultaModel.getPaciente().getNome();
        this.medico = consultaModel.getMedico().getNome();
        this.statusConsulta = consultaModel.getStatusConsulta().getDescricao();
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getObservacaoPaciente() {
        return observacaoPaciente;
    }

    public void setObservacaoPaciente(String observacaoPaciente) {
        this.observacaoPaciente = observacaoPaciente;
    }

    public String getObservacaoMedico() {
        return observacaoMedico;
    }

    public void setObservacaoMedico(String observacaoMedico) {
        this.observacaoMedico = observacaoMedico;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(String statusConsulta) {
        this.statusConsulta = statusConsulta;
    }
}
