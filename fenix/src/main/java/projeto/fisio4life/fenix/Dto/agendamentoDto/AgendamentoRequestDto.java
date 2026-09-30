package projeto.fisio4life.fenix.Dto.agendamentoDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoRequestDto {

    @NotNull
    private Integer idClinica;

    @NotNull
    private Integer idFisioterapeuta;

    @NotNull
    private Integer idPaciente;

    @NotNull
    private LocalDate dataAgendamento;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFim;

    @NotNull
    private Integer statusAgendamento;

    @Size(max = 255)
    private String observacao;
}
