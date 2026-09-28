package projeto.fisio4life.fenix.Dto;

import jakarta.validation.constraints.*;

public class EnderecoDto {

    public record Request(
            @NotBlank
            String logradouro,
            @NotNull
            @Positive
            Integer numero,
            @NotBlank
            String bairro,
            @NotBlank
            String cidade,
            @NotBlank
            @Size(min = 2, max = 2)
            String estado,
            @NotBlank
            @Size(max = 8, message = "CEP deve conter 8 dígitos")
            String cep
    ) {}

    public record Response(
            Long id,
            String logradouro,
            Integer numero,
            String bairro,
            String cidade,
            String estado,
            String cep
    ) {}
}