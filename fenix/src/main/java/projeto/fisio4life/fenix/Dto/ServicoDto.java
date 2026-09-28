package projeto.fisio4life.fenix.Dto;
import jakarta.validation.constraints.*;

public class ServicoDto {

    public record Request(
            @NotNull
            Long clinicaId,
            @NotBlank
            String nome,
            @NotNull
            @Positive
            Integer quantidadeSessoes,
            @NotNull
            @PositiveOrZero
            Double valor,
            @NotBlank
            String tipo
    ) {}

    public record Response(
            Long id,
            Long clinicaId,
            String nome,
            Integer quantidadeSessoes,
            Double valor,
            String tipo
    ) {}
}
