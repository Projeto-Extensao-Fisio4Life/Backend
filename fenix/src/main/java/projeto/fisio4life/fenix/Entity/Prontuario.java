package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_prontuario;
    @OneToOne
    @JoinColumn(name = "fk_paciente")
    private Paciente paciente;
    private LocalDateTime data_abertura;
    private Boolean status;
    private String observacao_inicial;

    public Prontuario() {
    }

    public Prontuario(Long id_prontuario, Paciente paciente, LocalDateTime data_abertura, Boolean status, String observacao_inicial) {
        this.id_prontuario = id_prontuario;
        this.paciente = paciente;
        this.data_abertura = data_abertura;
        this.status = status;
        this.observacao_inicial = observacao_inicial;
    }

    public Long getId_prontuario() {
        return id_prontuario;
    }

    public void setId_prontuario(Long id_prontuario) {
        this.id_prontuario = id_prontuario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(LocalDateTime data_abertura) {
        this.data_abertura = data_abertura;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getObservacao_inicial() {
        return observacao_inicial;
    }

    public void setObservacao_inicial(String observacao_inicial) {
        this.observacao_inicial = observacao_inicial;
    }
}
