package projeto.fisio4life.fenix.Dto.pacienteDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PacienteRequestDto {

    @NotNull
    private Integer idUsuario;

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotNull
    private Integer permiteAtendimentoGrupo;
}
