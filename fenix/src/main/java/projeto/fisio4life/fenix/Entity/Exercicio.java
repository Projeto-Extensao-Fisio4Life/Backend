package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exercicio")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exercicio", nullable = false)
    private Integer id_exercicio;

    @Column(name = "categoria", nullable = false, length = 45)
    private String categoria;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    public Exercicio() {}
    public Integer getId_exercicio() { return id_exercicio; }
    public void setId_exercicio(Integer valor) { this.id_exercicio = valor; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String valor) { this.categoria = valor; }
    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }
}
