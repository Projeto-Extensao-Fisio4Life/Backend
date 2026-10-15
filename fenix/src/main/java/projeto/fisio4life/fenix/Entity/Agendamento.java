package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
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
    private Byte status_agendamento;

    @Column(name = "observacao", nullable = true, length = 255)
    private String observacao;

    public Agendamento() {}
    public Integer getId_agendamento() { return id_agendamento; }
    public void setId_agendamento(Integer valor) { this.id_agendamento = valor; }
    public Clinica getClinica() { return clinica; }
    public void setClinica(Clinica valor) { this.clinica = valor; }
    public Fisioterapeuta getFisioterapeuta() { return fisioterapeuta; }
    public void setFisioterapeuta(Fisioterapeuta valor) { this.fisioterapeuta = valor; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente valor) { this.paciente = valor; }
    public LocalDate getData_agendamento() { return data_agendamento; }
    public void setData_agendamento(LocalDate valor) { this.data_agendamento = valor; }
    public LocalTime getHora_inicio() { return hora_inicio; }
    public void setHora_inicio(LocalTime valor) { this.hora_inicio = valor; }
    public LocalTime getHora_fim() { return hora_fim; }
    public void setHora_fim(LocalTime valor) { this.hora_fim = valor; }
    public Byte getStatus_agendamento() { return status_agendamento; }
    public void setStatus_agendamento(Byte valor) { this.status_agendamento = valor; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String valor) { this.observacao = valor; }
}
