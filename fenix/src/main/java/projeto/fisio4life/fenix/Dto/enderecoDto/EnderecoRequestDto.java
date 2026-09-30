package projeto.fisio4life.fenix.Dto.enderecoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnderecoRequestDto {

    @NotBlank
    @Size(max = 45)
    private String logradouro;

    @NotNull
    private Integer numero;

    @NotBlank
    @Size(max = 45)
    private String bairro;

    @NotBlank
    @Size(max = 45)
    private String cidade;

    @NotBlank
    @Size(max = 2)
    private String estado;

    @NotBlank
    @Size(max = 45)
    private String cep;
}
