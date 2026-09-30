package projeto.fisio4life.fenix.Dto.exercicioDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ExercicioRequestDto {

    @NotBlank
    @Size(max = 45)
    private String categoria;

    @NotBlank
    @Size(max = 45)
    private String nome;
}
