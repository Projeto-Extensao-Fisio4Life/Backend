package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "escala_perguntas")
public class EscalaPerguntas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escala_perguntas", nullable = false)
    private Integer id_escala_perguntas;

    @ManyToOne
    @JoinColumn(name = "fk_escala", nullable = false)
    private Escala escala;

    @Column(name = "titulo_pergunta", nullable = false, length = 45)
    private String titulo_pergunta;

    @Column(name = "tipo_dado", nullable = false, length = 45)
    private String tipo_dado;

    public EscalaPerguntas() {}
    public Integer getId_escala_perguntas() { return id_escala_perguntas; }
    public void setId_escala_perguntas(Integer valor) { this.id_escala_perguntas = valor; }
    public Escala getEscala() { return escala; }
    public void setEscala(Escala valor) { this.escala = valor; }
    public String getTitulo_pergunta() { return titulo_pergunta; }
    public void setTitulo_pergunta(String valor) { this.titulo_pergunta = valor; }
    public String getTipo_dado() { return tipo_dado; }
    public void setTipo_dado(String valor) { this.tipo_dado = valor; }
}
