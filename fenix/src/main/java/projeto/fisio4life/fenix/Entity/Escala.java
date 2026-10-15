package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "escala")
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escala", nullable = false)
    private Integer id_escala;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 45)
    private String descricao;

    public Escala() {}
    public Integer getId_escala() { return id_escala; }
    public void setId_escala(Integer valor) { this.id_escala = valor; }
    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String valor) { this.descricao = valor; }
}
