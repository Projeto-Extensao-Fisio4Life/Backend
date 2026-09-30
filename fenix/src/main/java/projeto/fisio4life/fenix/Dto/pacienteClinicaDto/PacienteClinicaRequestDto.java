package projeto.fisio4life.fenix.Dto.pacienteClinicaDto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PacienteClinicaRequestDto {

    @NotNull
    private Integer idClinica;

    @NotNull
    private Integer idPaciente;

    private LocalDateTime dataCadastro;

    private LocalDateTime ultimaAtualizacao;

    @NotNull
    private Integer statusPacienteClinica;
}
