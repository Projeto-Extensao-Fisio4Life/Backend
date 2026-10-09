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

    public AgendamentoRequestDto() {
    }

    public AgendamentoRequestDto(Integer idClinica, Integer idFisioterapeuta, Integer idPaciente, LocalDate dataAgendamento, LocalTime horaInicio, LocalTime horaFim, Integer statusAgendamento, String observacao) {
        this.idClinica = idClinica;
        this.idFisioterapeuta = idFisioterapeuta;
        this.idPaciente = idPaciente;
        this.dataAgendamento = dataAgendamento;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.statusAgendamento = statusAgendamento;
        this.observacao = observacao;
    }


    public Integer getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Integer idClinica) {
        this.idClinica = idClinica;
    }

    public Integer getIdFisioterapeuta() {
        return idFisioterapeuta;
    }

    public void setIdFisioterapeuta(Integer idFisioterapeuta) {
        this.idFisioterapeuta = idFisioterapeuta;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public Integer getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(Integer statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
