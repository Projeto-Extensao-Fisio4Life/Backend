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

    public AgendamentoResponseDto() {
    }

    public AgendamentoResponseDto(Integer idAgendamento, Integer idClinica, Integer idFisioterapeuta, Integer idPaciente, LocalDate dataAgendamento, LocalTime horaInicio, LocalTime horaFim, Integer statusAgendamento, String observacao) {
        this.idAgendamento = idAgendamento;
        this.idClinica = idClinica;
        this.idFisioterapeuta = idFisioterapeuta;
        this.idPaciente = idPaciente;
        this.dataAgendamento = dataAgendamento;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.statusAgendamento = statusAgendamento;
        this.observacao = observacao;
    }

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
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
