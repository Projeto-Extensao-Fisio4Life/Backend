package projeto.fisio4life.fenix.Dto.fisioterapeutaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FisioterapeutaRequestDto {

    @NotNull
    private Integer idUsuario;

    @NotBlank
    @Size(max = 10)
    private String crefito;

    @NotBlank
    @Size(max = 45)
    private String especialidade;

    @NotBlank
    @Size(max = 14)
    private String cnpj;
}
