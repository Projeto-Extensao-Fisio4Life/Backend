package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pacote_paciente")
public class PacotePaciente {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pacote_paciente;
    @ManyToOne
    @JoinColumn(name = "fk_servico")
    private Servico servico;
    @ManyToOne
    @JoinColumn(name = "fk_paciente")
    private Paciente paciente;
    private Integer sessoes_restantes;
    private LocalDateTime data_compra;
    private Integer status;

    public PacotePaciente() {
    }

    public PacotePaciente(Integer id_pacote_paciente, Servico servico, Paciente paciente, Integer sessoes_restantes, LocalDateTime data_compra, Integer status) {
        this.id_pacote_paciente = id_pacote_paciente;
        this.servico = servico;
        this.paciente = paciente;
        this.sessoes_restantes = sessoes_restantes;
        this.data_compra = data_compra;
        this.status = status;
    }

    public Integer getId_pacote_paciente() {
        return id_pacote_paciente;
    }

    public void setId_pacote_paciente(Integer id_pacote_paciente) {
        this.id_pacote_paciente = id_pacote_paciente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Integer getSessoes_restantes() {
        return sessoes_restantes;
    }

    public void setSessoes_restantes(Integer sessoes_restantes) {
        this.sessoes_restantes = sessoes_restantes;
    }

    public LocalDateTime getData_compra() {
        return data_compra;
    }

    public void setData_compra(LocalDateTime data_compra) {
        this.data_compra = data_compra;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
