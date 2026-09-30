package projeto.fisio4life.fenix.Dto.prontuarioDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class ProntuarioRequestDto {

    @NotNull
    private Integer idPaciente;

    private LocalDateTime dataAbertura;

    @NotNull
    private Integer statusProntuario;

    @Size(max = 255)
    private String observacaoInicial;
}
