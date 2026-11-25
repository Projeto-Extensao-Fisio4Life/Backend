package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento", nullable = false)
    private Integer id_agendamento;

    @ManyToOne
    @JoinColumn(name = "fk_clinica", nullable = false)
    private Clinica clinica;

    @ManyToOne
    @JoinColumn(name = "fk_fisioterapeuta", nullable = false)
    private Fisioterapeuta fisioterapeuta;

    @ManyToOne
    @JoinColumn(name = "fk_paciente", nullable = false)
    private Paciente paciente;

    @Column(name = "data_agendamento", nullable = false)
    private LocalDate data_agendamento;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime hora_inicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime hora_fim;

    @Column(name = "status_agendamento", nullable = false, columnDefinition = "TINYINT")
    private RegraStatusEnum status_agendamento;

    @Column(name = "observacao", nullable = true, length = 255)
    private String observacao;

    public Agendamento() {}

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

    public LocalDate getData_agendamento() {
        return data_agendamento;
    }

    public void setData_agendamento(LocalDate data_agendamento) {
        this.data_agendamento = data_agendamento;
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

    public RegraStatusEnum getStatus_agendamento() {
        return status_agendamento;
    }

    public void setStatus_agendamento(RegraStatusEnum status_agendamento) {
        this.status_agendamento = status_agendamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
