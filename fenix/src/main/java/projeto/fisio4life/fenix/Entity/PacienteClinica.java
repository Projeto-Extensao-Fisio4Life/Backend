package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "paciente_clinica")
public class PacienteClinica {
    @Id@ GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id_vinculo;
    @ManyToOne
    @JoinColumn(name = "fk_clinica")
   private Clinica clinica;
    @ManyToOne
    @JoinColumn(name = "fk_paciente")
   private Paciente paciente;
   private LocalDateTime data_cadastro;
   private LocalDateTime utlima_atualizacao;
   private Boolean status_paciente_clinica;

    public PacienteClinica() {
    }

    public PacienteClinica(Integer id_vinculo, Clinica clinica, Paciente paciente, LocalDateTime data_cadastro, LocalDateTime utlima_atualizacao, Boolean status_paciente_clinica) {
        this.id_vinculo = id_vinculo;
        this.clinica = clinica;
        this.paciente = paciente;
        this.data_cadastro = data_cadastro;
        this.utlima_atualizacao = utlima_atualizacao;
        this.status_paciente_clinica = status_paciente_clinica;
    }

    public Integer getId_vinculo() {
        return id_vinculo;
    }

    public void setId_vinculo(Integer id_vinculo) {
        this.id_vinculo = id_vinculo;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public LocalDateTime getUtlima_atualizacao() {
        return utlima_atualizacao;
    }

    public void setUtlima_atualizacao(LocalDateTime utlima_atualizacao) {
        this.utlima_atualizacao = utlima_atualizacao;
    }

    public Boolean getStatus_paciente_clinica() {
        return status_paciente_clinica;
    }

    public void setStatus_paciente_clinica(Boolean status_paciente_clinica) {
        this.status_paciente_clinica = status_paciente_clinica;
    }
}
