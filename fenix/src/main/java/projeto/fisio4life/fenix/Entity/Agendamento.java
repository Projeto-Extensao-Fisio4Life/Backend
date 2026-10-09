package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Entity
@Table
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_agendamento;
    @ManyToOne
    private Clinica clinica;
    @ManyToOne
    private Fisioterapeuta fisioterapeuta;
    @ManyToOne
    private Paciente paciente;
    private LocalDate data;
    private LocalDate data_agendamento;
    private LocalTime hora_inicio;
    private LocalTime hora_fim;
    private Integer status;
    private String observacao;

    public Agendamento() {
    }

    public Agendamento(Integer id_agendamento, Clinica clinica, Fisioterapeuta fisioterapeuta, Paciente paciente, LocalDate data, LocalDate data_agendamento, LocalTime hora_inicio, LocalTime hora_fim, Integer status, String observacao) {
        this.id_agendamento = id_agendamento;
        this.clinica = clinica;
        this.fisioterapeuta = fisioterapeuta;
        this.paciente = paciente;
        this.data = data;
        this.data_agendamento = data_agendamento;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.status = status;
        this.observacao = observacao;
    }

    public LocalDate getData_agendamento() {
        return data_agendamento;
    }

    public void setData_agendamento(LocalDate data_agendamento) {
        this.data_agendamento = data_agendamento;
    }

    public Integer getId_agendamento() {
        return id_agendamento;
    }

    public void setId_agendamento(Integer id_agendamento) {
        this.id_agendamento = id_agendamento;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Fisioterapeuta getFisioterapeuta() {
        return fisioterapeuta;
    }

    public void setFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        this.fisioterapeuta = fisioterapeuta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(LocalTime hora_fim) {
        this.hora_fim = hora_fim;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
