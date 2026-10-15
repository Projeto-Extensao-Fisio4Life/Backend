package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "escala_prontuario")
public class EscalaProntuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escala_prontuario", nullable = false)
    private Integer id_escala_prontuario;

    @ManyToOne
    @JoinColumn(name = "fk_prontuario", nullable = false)
    private Prontuario prontuario;

    @ManyToOne
    @JoinColumn(name = "fk_escala", nullable = false)
    private Escala escala;

    @Column(name = "data_resposta", nullable = false)
    private LocalDateTime data_resposta;

    public EscalaProntuario() {}
    public Integer getId_escala_prontuario() { return id_escala_prontuario; }
    public void setId_escala_prontuario(Integer valor) { this.id_escala_prontuario = valor; }
    public Prontuario getProntuario() { return prontuario; }
    public void setProntuario(Prontuario valor) { this.prontuario = valor; }
    public Escala getEscala() { return escala; }
    public void setEscala(Escala valor) { this.escala = valor; }
    public LocalDateTime getData_resposta() { return data_resposta; }
    public void setData_resposta(LocalDateTime valor) { this.data_resposta = valor; }
}
