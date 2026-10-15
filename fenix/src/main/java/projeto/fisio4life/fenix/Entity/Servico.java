package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico", nullable = false)
    private Integer id_servico;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "quantidade_sessoes", nullable = false)
    private Integer quantidade_sessoes;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "status_servico", nullable = false, columnDefinition = "TINYINT")
    private Byte status_servico;

    public Servico() {}
    public Integer getId_servico() { return id_servico; }
    public void setId_servico(Integer valor) { this.id_servico = valor; }
    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }
    public Integer getQuantidade_sessoes() { return quantidade_sessoes; }
    public void setQuantidade_sessoes(Integer valor) { this.quantidade_sessoes = valor; }
    public String getTipo() { return tipo; }
    public void setTipo(String valor) { this.tipo = valor; }
    public Byte getStatus_servico() { return status_servico; }
    public void setStatus_servico(Byte valor) { this.status_servico = valor; }
}
