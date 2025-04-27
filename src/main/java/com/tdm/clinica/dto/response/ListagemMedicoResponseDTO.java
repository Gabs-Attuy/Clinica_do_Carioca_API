package com.tdm.clinica.dto.response;

import com.tdm.clinica.model.MedicoModel;

public class ListagemMedicoResponseDTO {

    private Long id;
    private String nome;
    private String crm;
    private String especialidade;

    public ListagemMedicoResponseDTO(MedicoModel medicos) {
        this.id = medicos.getId();
        this.nome = medicos.getNome();
        this.crm = medicos.getCrm();
        this.especialidade = medicos.getEspecialidade();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
