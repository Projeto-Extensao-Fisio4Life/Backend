package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "escala_respostas")
public class EscalaRespostas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escala_respostas", nullable = false)
    private Integer id_escala_respostas;

    @ManyToOne
    @JoinColumn(name = "fk_escala_prontuario", nullable = false)
    private EscalaProntuario escala_prontuario;

    @ManyToOne
    @JoinColumn(name = "fk_escala_perguntas", nullable = false)
    private EscalaPerguntas escala_perguntas;

    @Column(name = "valor_resposta", nullable = false, length = 45)
    private String valor_resposta;

    public EscalaRespostas() {}
    public Integer getId_escala_respostas() { return id_escala_respostas; }
    public void setId_escala_respostas(Integer valor) { this.id_escala_respostas = valor; }
    public EscalaProntuario getEscala_prontuario() { return escala_prontuario; }
    public void setEscala_prontuario(EscalaProntuario valor) { this.escala_prontuario = valor; }
    public EscalaPerguntas getEscala_perguntas() { return escala_perguntas; }
    public void setEscala_perguntas(EscalaPerguntas valor) { this.escala_perguntas = valor; }
    public String getValor_resposta() { return valor_resposta; }
    public void setValor_resposta(String valor) { this.valor_resposta = valor; }
}
