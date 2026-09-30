package projeto.fisio4life.fenix.Dto.clinicaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClinicaRequestDto {

    @NotNull
    private Integer idContato;

    @NotNull
    private Integer idEndereco;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Size(max = 14)
    private String cnpj;
}
