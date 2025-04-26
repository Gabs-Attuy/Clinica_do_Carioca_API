package com.tdm.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Medico",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_medico_crm", columnNames = "crm"),
                @UniqueConstraint(name = "UK_medico_telefone", columnNames = "telefone")
        })
public class MedicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;
    private String especialidade;
    private String telefone;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credencial_id")
    private CredencialModel credencialModel;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public CredencialModel getCredencialModel() {
        return credencialModel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCredencialModel(CredencialModel credencialModel) {
        this.credencialModel = credencialModel;
    }
}
