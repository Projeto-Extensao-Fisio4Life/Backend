package projeto.fisio4life.fenix.Dto.pacotePacienteDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PacotePacienteRequestDto {

    @NotNull
    private Integer idServico;

    @NotNull
    private Integer idPaciente;

    @NotBlank
    private String sessoesRestantes;

    @NotNull
    private LocalDateTime dataCompra;

    @NotNull
    private Integer statusPacotePaciente;
}
