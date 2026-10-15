package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evolucao")
public class Evolucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evolucao", nullable = false)
    private Integer id_evolucao;

    @ManyToOne
    @JoinColumn(name = "fk_sessao", nullable = false)
    private Sessao sessao;

    @ManyToOne
    @JoinColumn(name = "fk_prontuario", nullable = false)
    private Prontuario prontuario;

    @Column(name = "data_evolucao", nullable = false)
    private LocalDateTime data_evolucao;

    @Column(name = "conduta", nullable = false, length = 255)
    private String conduta;

    @Column(name = "avaliacao", nullable = false, length = 255)
    private String avaliacao;

    @Column(name = "observacao", nullable = false, length = 255)
    private String observacao;

    public Evolucao() {}
    public Integer getId_evolucao() { return id_evolucao; }
    public void setId_evolucao(Integer valor) { this.id_evolucao = valor; }
    public Sessao getSessao() { return sessao; }
    public void setSessao(Sessao valor) { this.sessao = valor; }
    public Prontuario getProntuario() { return prontuario; }
    public void setProntuario(Prontuario valor) { this.prontuario = valor; }
    public LocalDateTime getData_evolucao() { return data_evolucao; }
    public void setData_evolucao(LocalDateTime valor) { this.data_evolucao = valor; }
    public String getConduta() { return conduta; }
    public void setConduta(String valor) { this.conduta = valor; }
    public String getAvaliacao() { return avaliacao; }
    public void setAvaliacao(String valor) { this.avaliacao = valor; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String valor) { this.observacao = valor; }
}
