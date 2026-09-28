package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

// --- ENTITY ---
@Entity
@Table
public class Evolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evolucao;

    @OneToOne
    @JoinColumn(name = "fk_sessao")
    private Sessao sessao;
    @ManyToOne
    @JoinColumn(name = "fk_prontuario")
    private Prontuario prontuario;
    private LocalDateTime data_evolucao;
    private String conduta;
    private String avaliacao;
    private String observacao;

    public Evolucao() {
    }

    public Evolucao(Long id_evolucao, Sessao sessao, Prontuario prontuario, LocalDateTime data_evolucao, String conduta, String avaliacao, String observacao) {
        this.id_evolucao = id_evolucao;
        this.sessao = sessao;
        this.prontuario = prontuario;
        this.data_evolucao = data_evolucao;
        this.conduta = conduta;
        this.avaliacao = avaliacao;
        this.observacao = observacao;
    }

    public Long getId_evolucao() {
        return id_evolucao;
    }

    public void setId_evolucao(Long id_evolucao) {
        this.id_evolucao = id_evolucao;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public LocalDateTime getData_evolucao() {
        return data_evolucao;
    }

    public void setData_evolucao(LocalDateTime data_evolucao) {
        this.data_evolucao = data_evolucao;
    }

    public String getConduta() {
        return conduta;
    }

    public void setConduta(String conduta) {
        this.conduta = conduta;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}