package projeto.fisio4life.fenix.Dto.agendamentoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public AgendamentoResponseDto() {}

    public Integer getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(Integer valor) { this.idAgendamento = valor; }

    public Integer getIdClinica() { return idClinica; }
    public void setIdClinica(Integer valor) { this.idClinica = valor; }

    public Integer getIdFisioterapeuta() { return idFisioterapeuta; }
    public void setIdFisioterapeuta(Integer valor) { this.idFisioterapeuta = valor; }

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer valor) { this.idPaciente = valor; }

    public LocalDate getDataAgendamento() { return dataAgendamento; }
    public void setDataAgendamento(LocalDate valor) { this.dataAgendamento = valor; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime valor) { this.horaInicio = valor; }

    public LocalTime getHoraFim() { return horaFim; }
    public void setHoraFim(LocalTime valor) { this.horaFim = valor; }

    public Integer getStatusAgendamento() { return statusAgendamento; }
    public void setStatusAgendamento(Integer valor) { this.statusAgendamento = valor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String valor) { this.observacao = valor; }

}
