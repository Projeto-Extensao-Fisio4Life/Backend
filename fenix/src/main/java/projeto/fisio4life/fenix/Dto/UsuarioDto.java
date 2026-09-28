package projeto.fisio4life.fenix.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class UsuarioDto {

    public record Request(
            @NotBlank
            String nome,
            @NotBlank
            @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
            String senha,
            @NotBlank
            String tipoUsuario,
            @NotNull
            Long enderecoId,
            @NotNull
            Long contatoId
    ) {}

    public record Response(
            Long id,
            String nome,
            String tipoUsuario,
            LocalDateTime dataCadastro,
            Boolean statusUsuario
    ) {}
}
