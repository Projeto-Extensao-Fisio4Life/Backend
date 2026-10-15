package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prontuario")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prontuario", nullable = false)
    private Integer id_prontuario;

    @OneToOne
    @JoinColumn(name = "fk_paciente", nullable = false, unique = true)
    private Paciente paciente;

    @Column(name = "data_abertura")
    private LocalDateTime data_abertura;

    @Column(name = "status_prontuario", nullable = false, columnDefinition = "TINYINT")
    private Byte status_prontuario;

    @Column(name = "observacao_inicial", length = 255)
    private String observacao_inicial;

    @PrePersist
    void preencherDataPadrao() {
        if (data_abertura == null) data_abertura = LocalDateTime.now();
    }

    public Prontuario() {}
    public Integer getId_prontuario() { return id_prontuario; }
    public void setId_prontuario(Integer valor) { this.id_prontuario = valor; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente valor) { this.paciente = valor; }
    public LocalDateTime getData_abertura() { return data_abertura; }
    public void setData_abertura(LocalDateTime valor) { this.data_abertura = valor; }
    public Byte getStatus_prontuario() { return status_prontuario; }
    public void setStatus_prontuario(Byte valor) { this.status_prontuario = valor; }
    public String getObservacao_inicial() { return observacao_inicial; }
    public void setObservacao_inicial(String valor) { this.observacao_inicial = valor; }
}
