package projeto.fisio4life.fenix.Dto;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PacienteDto {

    public record Request(
            @NotNull
            Long usuarioId,
            @NotNull
            @Past
            LocalDate dataNascimento,
            @NotBlank
            @CPF
            String cpf,
            @NotNull
            Boolean permiteAtendimentoGrupo
    ) {}

    public record Response(
            Long id,
            Long usuarioId,
            LocalDate dataNascimento,
            String cpf,
            Boolean permiteAtendimentoGrupo
    ) {}
}