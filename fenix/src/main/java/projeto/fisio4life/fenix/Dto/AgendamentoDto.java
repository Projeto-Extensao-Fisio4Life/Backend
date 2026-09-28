package projeto.fisio4life.fenix.Dto;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoDto {

    public record Request(
            @NotNull
            Long clinicaId,
            @NotNull
            Long fisioterapeutaId,
            @NotNull
            Long pacienteId,
            @NotNull
            @FutureOrPresent
            LocalDate data,
            @NotNull
            LocalTime horaInicio,
            @NotNull
            LocalTime horaFim,
            @NotNull
            Integer status,
            String observacao
    ) {}

    public record Response(
            Long id,
            LocalDate data,
            LocalTime horaInicio,
            LocalTime horaFim,
            Integer status,
            String observacao
    ) {}
}
