package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sessao;
    @OneToOne
    @JoinColumn(name = "fk_agendamento")
    private Agendamento agendamento;
    private LocalDateTime data_sessao;
    private String descricao;
    private String observacao;

    public Sessao() {
    }

    public Sessao(Long id_sessao, Agendamento agendamento, LocalDateTime data_sessao, String descricao, String observacao) {
        this.id_sessao = id_sessao;
        this.agendamento = agendamento;
        this.data_sessao = data_sessao;
        this.descricao = descricao;
        this.observacao = observacao;
    }

    public Long getId_sessao() {
        return id_sessao;
    }

    public void setId_sessao(Long id_sessao) {
        this.id_sessao = id_sessao;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public LocalDateTime getData_sessao() {
        return data_sessao;
    }

    public void setData_sessao(LocalDateTime data_sessao) {
        this.data_sessao = data_sessao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
