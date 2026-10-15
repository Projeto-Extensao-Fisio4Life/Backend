package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "exercicio_passado")
public class ExercicioPassado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exercicio_passado", nullable = false)
    private Integer id_exercicio_passado;

    @ManyToOne
    @JoinColumn(name = "fk_exercicio", nullable = false)
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "fk_evolucao", nullable = false)
    private Evolucao evolucao;

    @Column(name = "segmento", nullable = false, length = 45)
    private String segmento;

    @Column(name = "quantidade_series", nullable = false)
    private Integer quantidade_series;

    @Column(name = "quantidade_repeticoes", nullable = false)
    private Integer quantidade_repeticoes;

    @Column(name = "carga", nullable = false)
    private Integer carga;

    @Column(name = "tempo", nullable = false)
    private LocalTime tempo;

    @Column(name = "local", nullable = false, length = 45)
    private String local;

    public ExercicioPassado() {}
    public Integer getId_exercicio_passado() { return id_exercicio_passado; }
    public void setId_exercicio_passado(Integer valor) { this.id_exercicio_passado = valor; }
    public Exercicio getExercicio() { return exercicio; }
    public void setExercicio(Exercicio valor) { this.exercicio = valor; }
    public Evolucao getEvolucao() { return evolucao; }
    public void setEvolucao(Evolucao valor) { this.evolucao = valor; }
    public String getSegmento() { return segmento; }
    public void setSegmento(String valor) { this.segmento = valor; }
    public Integer getQuantidade_series() { return quantidade_series; }
    public void setQuantidade_series(Integer valor) { this.quantidade_series = valor; }
    public Integer getQuantidade_repeticoes() { return quantidade_repeticoes; }
    public void setQuantidade_repeticoes(Integer valor) { this.quantidade_repeticoes = valor; }
    public Integer getCarga() { return carga; }
    public void setCarga(Integer valor) { this.carga = valor; }
    public LocalTime getTempo() { return tempo; }
    public void setTempo(LocalTime valor) { this.tempo = valor; }
    public String getLocal() { return local; }
    public void setLocal(String valor) { this.local = valor; }
}
