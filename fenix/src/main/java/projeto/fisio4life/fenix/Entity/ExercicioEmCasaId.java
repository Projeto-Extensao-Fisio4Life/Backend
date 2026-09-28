package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ExercicioEmCasaId implements Serializable {
    @Column(name = "fk_evolucao") private Long evolucaoId;
    @Column(name = "fk_exercicio") private Long exercicioId;

    public ExercicioEmCasaId() {
    }

    public ExercicioEmCasaId(Long evolucaoId, Long exercicioId) {
        this.evolucaoId = evolucaoId;
        this.exercicioId = exercicioId;
    }

    public Long getEvolucaoId() {
        return evolucaoId;
    }

    public void setEvolucaoId(Long evolucaoId) {
        this.evolucaoId = evolucaoId;
    }

    public Long getExercicioId() {
        return exercicioId;
    }

    public void setExercicioId(Long exercicioId) {
        this.exercicioId = exercicioId;
    }
}