package projeto.fisio4life.fenix.Entity;


import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "exercicio_passado")
public class ExercicioPassado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_exercicio_passado;

    @ManyToOne
    @JoinColumn(name = "fk_evolucao")
    private Evolucao evolucao;
    @ManyToOne
    @JoinColumn(name = "fk_exercicio")
    private Exercicio exercicio;
    private String segmento;
    private Integer quantidade_series;
    private Integer quantidade_repeticoes;
    private Integer carga;
    private Time tempo;

    public ExercicioPassado() {
    }

    public ExercicioPassado(Integer id_exercicio_passado, Evolucao evolucao, Exercicio exercicio, String segmento, Integer quantidade_series, Integer quantidade_repeticoes, Integer carga, Time tempo) {
        this.id_exercicio_passado = id_exercicio_passado;
        this.evolucao = evolucao;
        this.exercicio = exercicio;
        this.segmento = segmento;
        this.quantidade_series = quantidade_series;
        this.quantidade_repeticoes = quantidade_repeticoes;
        this.carga = carga;
        this.tempo = tempo;
    }

    public Integer getId_exercicio_passado() {
        return id_exercicio_passado;
    }

    public void setId_exercicio_passado(Integer id_exercicio_passado) {
        this.id_exercicio_passado = id_exercicio_passado;
    }

    public Evolucao getEvolucao() {
        return evolucao;
    }

    public void setEvolucao(Evolucao evolucao) {
        this.evolucao = evolucao;
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

    public Time getTempo() {
        return tempo;
    }

    public void setTempo(Time tempo) {
        this.tempo = tempo;
    }
}
