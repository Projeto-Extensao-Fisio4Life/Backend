package projeto.fisio4life.fenix.Dto.agendamentoDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoResponseDto {

    private Integer idAgendamento;
    private Integer idClinica;
    private Integer idFisioterapeuta;
    private Integer idPaciente;
    private LocalDate dataAgendamento;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Integer statusAgendamento;
    private String observacao;
}
