package projeto.fisio4life.fenix.Dto;

import jakarta.validation.constraints.*;

public class ContatoDto {

    public record Request(
            @Size(max = 10, message = "Telefone inválido")
            String telefone,
            @NotBlank
            @Size(max = 11, message = "Celular inválido")
            String celular,
            @NotBlank
            @Email
            String email
    ) {}

    public record Response(
            Long id,
            String telefone,
            String celular,
            String email
    ) {}
}