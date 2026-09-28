package projeto.fisio4life.fenix.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "exercicio_na_sessao")
public class ExercicioNaSessao {
    @EmbeddedId
    private ExercicioNaSessaoId id = new ExercicioNaSessaoId();
    @ManyToOne @MapsId("sessaoId")
    @JoinColumn(name = "fk_sessao")
    private Sessao sessao;
    @ManyToOne @MapsId("exercicioId")
    @JoinColumn(name = "fk_exercicio")
    private Exercicio exercicio;
    private String segmento;
    private Integer quantidade_series;
    private Integer quantidade_repeticoes;
    private Integer carga;
    private LocalTime tempo;

    public ExercicioNaSessao() {
    }

    public ExercicioNaSessao(ExercicioNaSessaoId id, Sessao sessao, Exercicio exercicio, String segmento, Integer quantidade_series, Integer quantidade_repeticoes, Integer carga, LocalTime tempo) {
        this.id = id;
        this.sessao = sessao;
        this.exercicio = exercicio;
        this.segmento = segmento;
        this.quantidade_series = quantidade_series;
        this.quantidade_repeticoes = quantidade_repeticoes;
        this.carga = carga;
        this.tempo = tempo;
    }

    public ExercicioNaSessaoId getId() {
        return id;
    }

    public void setId(ExercicioNaSessaoId id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public Integer getQuantidade_series() {
        return quantidade_series;
    }

    public void setQuantidade_series(Integer quantidade_series) {
        this.quantidade_series = quantidade_series;
    }

    public Integer getQuantidade_repeticoes() {
        return quantidade_repeticoes;
    }

    public void setQuantidade_repeticoes(Integer quantidade_repeticoes) {
        this.quantidade_repeticoes = quantidade_repeticoes;
    }

    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
    }

    public LocalTime getTempo() {
        return tempo;
    }

    public void setTempo(LocalTime tempo) {
        this.tempo = tempo;
    }
}