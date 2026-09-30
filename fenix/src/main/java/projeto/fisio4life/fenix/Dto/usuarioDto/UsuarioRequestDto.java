package projeto.fisio4life.fenix.Dto.usuarioDto;

import dto.enderecoDto.EnderecoRequestDto;
import dto.contatoDto.ContatoRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class UsuarioRequestDto {

    @Valid
    @NotNull
    private EnderecoRequestDto endereco;

    @Valid
    @NotNull
    private ContatoRequestDto contato;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    @Size(max = 60)
    private String senha;

    @NotBlank
    @Size(max = 45)
    private String tipoUsuario;

    @NotNull
    private Integer statusUsuario;
}
