package com.tdm.clinica.model;

import com.tdm.clinica.dto.ConsultaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Status_Consulta")
public class StatusConsultaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Column(columnDefinition = "TEXT")
    private String observacaoMedico;
    private LocalDateTime data_alteracao;

    public StatusConsultaModel() {
    }

    public StatusConsultaModel(Long id, String descricao, String observacaoMedico, LocalDateTime data_alteracao) {
        this.id = id;
        this.descricao = descricao;
        this.observacaoMedico = observacaoMedico;
        this.data_alteracao = data_alteracao;
    }

    public StatusConsultaModel(ConsultaDTO dto) {
        this.descricao = "Agendada";
        this.data_alteracao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getObservacaoMedico() {
        return observacaoMedico;
    }

    public LocalDateTime getData_alteracao() {
        return data_alteracao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setObservacaoMedico(String observacaoMedico) {
        this.observacaoMedico = observacaoMedico;
    }

    public void setData_alteracao(LocalDateTime data_alteracao) {
        this.data_alteracao = data_alteracao;
    }
}
