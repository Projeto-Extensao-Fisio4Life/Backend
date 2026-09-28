package projeto.fisio4life.fenix.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalTime;

// --- EMBEDDED IDs (Chaves Compostas) ---
@Embeddable
public class ExercicioNaSessaoId implements Serializable {
    @Column(name = "fk_sessao") private Long sessaoId;
    @Column(name = "fk_exercicio") private Long exercicioId;

    public ExercicioNaSessaoId() {
    }

    public ExercicioNaSessaoId(Long sessaoId, Long exercicioId) {
        this.sessaoId = sessaoId;
        this.exercicioId = exercicioId;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public Long getExercicioId() {
        return exercicioId;
    }

    public void setExercicioId(Long exercicioId) {
        this.exercicioId = exercicioId;
    }
}
