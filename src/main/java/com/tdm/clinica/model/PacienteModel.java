package com.tdm.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Paciente",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_paciente_cpf", columnNames = "cpf"),
                @UniqueConstraint(name = "UK_paciente_telefone", columnNames = "telefone")
        })
public class PacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Date DataNascimento;
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

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return DataNascimento;
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(Date dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCredencialModel(CredencialModel credencialModel) {
        this.credencialModel = credencialModel;
    }
}