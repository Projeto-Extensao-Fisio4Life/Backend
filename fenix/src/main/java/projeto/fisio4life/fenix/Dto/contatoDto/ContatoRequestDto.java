package projeto.fisio4life.fenix.Dto.contatoDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContatoRequestDto {

    @NotBlank
    @Size(max = 14)
    private String telefone;

    @NotBlank
    @Size(max = 14)
    private String celular;

    @NotBlank
    @Email
    @Size(max = 45)
    private String email;
}
