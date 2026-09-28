package projeto.fisio4life.fenix.Entity;


import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "exercicio_em_casa")
public class ExercicioEmCasa {
    @EmbeddedId
    private ExercicioEmCasaId id = new ExercicioEmCasaId();

    @ManyToOne
    @MapsId("evolucaoId")
    @JoinColumn(name = "fk_evolucao")
    private Evolucao evolucao;
    @ManyToOne @MapsId("exercicioId")
    @JoinColumn(name = "fk_exercicio") private Exercicio exercicio;

    private String segmento;
    @Column(name = "quantidade_series")
    private Integer quantidadeSeries;
    @Column(name = "quantidade_repeticoes")
    private Integer quantidadeRepeticoes;
    private Integer carga;
    private LocalTime tempo;
}
