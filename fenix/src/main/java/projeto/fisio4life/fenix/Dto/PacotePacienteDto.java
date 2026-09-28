package projeto.fisio4life.fenix.Dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class PacotePacienteDto {

    public record Request(
            @NotNull
            Long servicoId,
            @NotNull
            Long pacienteId,
            @NotNull
            @Min(value = 0, message = "Sessões restantes não podem ser negativas")
            Integer sessoesRestantes
    ) {}

    public record Response(
            Long id,
            Long servicoId,
            Long pacienteId,
            Integer sessoesRestantes,
            LocalDateTime dataCompra,
            Boolean status
    ) {}
}
