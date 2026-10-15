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

    public AgendamentoRequestDto() {}

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
