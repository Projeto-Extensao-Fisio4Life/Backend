package projeto.fisio4life.fenix.Dto;

import org.hibernate.validator.constraints.br.CNPJ;
import jakarta.validation.constraints.*;

public class FisioterapeutaDto {

    public record Request(
            @NotNull
            Long usuarioId,
            @NotBlank
            String crefito,
            @NotBlank
            String especialidade,
            @CNPJ
            String cnpj
    ) {}

    public record Response(
            Long id,
            Long usuarioId,
            String crefito,
            String especialidade,
            String cnpj
    ) {}
}