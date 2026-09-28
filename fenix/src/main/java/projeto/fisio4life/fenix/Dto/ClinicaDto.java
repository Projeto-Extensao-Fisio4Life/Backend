package projeto.fisio4life.fenix.Dto;
import org.hibernate.validator.constraints.br.CNPJ;
import jakarta.validation.constraints.*;

public class ClinicaDto {

    public record Request(
            @NotBlank
            String nome,
            @NotBlank
            @CNPJ
            String cnpj,
            @NotNull
            Long contatoId,
            @NotNull
            Long enderecoId
    ) {}

    public record Response(
            Long id,
            String nome,
            String cnpj,
            Long contatoId,
            Long enderecoId
    ) {}
}
