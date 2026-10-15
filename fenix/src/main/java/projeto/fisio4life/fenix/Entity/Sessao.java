package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sessao", nullable = false)
    private Integer id_sessao;

    @ManyToOne
    @JoinColumn(name = "fk_agendamento", nullable = false)
    private Agendamento agendamento;

    @ManyToOne
    @JoinColumn(name = "fk_servico", nullable = false)
    private Servico servico;

    @Column(name = "data_sessao", nullable = false)
    private LocalDateTime data_sessao;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "observacao", nullable = false, length = 255)
    private String observacao;

    public Sessao() {}
    public Integer getId_sessao() { return id_sessao; }
    public void setId_sessao(Integer valor) { this.id_sessao = valor; }
    public Agendamento getAgendamento() { return agendamento; }
    public void setAgendamento(Agendamento valor) { this.agendamento = valor; }
    public Servico getServico() { return servico; }
    public void setServico(Servico valor) { this.servico = valor; }
    public LocalDateTime getData_sessao() { return data_sessao; }
    public void setData_sessao(LocalDateTime valor) { this.data_sessao = valor; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String valor) { this.descricao = valor; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String valor) { this.observacao = valor; }
}
