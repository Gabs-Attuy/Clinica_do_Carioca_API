package com.tdm.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "Paciente")
public class PacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "cpf", unique = true)
    private String cpf;
    private Date DataNascimento;
    @Column(name = "telefone", unique = true)
    private String telefone;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "credencial_id")
    private CredencialModel credencialModel;
}
