package projeto.fisio4life.fenix.Dto;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import java.time.LocalDateTime;

public class ProntuarioDto {

    public record Request(
            @NotNull
            Long pacienteId,
            @NotBlank
            String observacaoInicial
    ) {}

    public record Response(
            Long id,
            Long pacienteId,
            LocalDateTime dataAbertura,
            Boolean status,
            String observacaoInicial
    ) {}
}
