package projeto.fisio4life.fenix.Dto.exercicioEmCasaDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;

public class ExercicioEmCasaRequestDto {

    @NotNull
    private Integer idEvolucao;

    @NotNull
    private Integer idExercicio;

    @Size(max = 45)
    private String segmento;

    private Integer quantidadeSeries;

    private Integer quantidadeRepeticoes;

    private Integer carga;

    private LocalTime tempo;
}
