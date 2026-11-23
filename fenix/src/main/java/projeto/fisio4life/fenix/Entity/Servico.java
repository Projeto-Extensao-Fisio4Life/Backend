package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;

import java.math.BigDecimal;

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
    private RegraStatusEnum status_servico;

    @Column(name = "valor_sessao", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor_sessao;


    public Servico() {}
    public Integer getId_servico() { return id_servico; }
    public void setId_servico(Integer valor) { this.id_servico = valor; }
    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }
    public Integer getQuantidade_sessoes() { return quantidade_sessoes; }
    public void setQuantidade_sessoes(Integer valor) { this.quantidade_sessoes = valor; }
    public String getTipo() { return tipo; }
    public void setTipo(String valor) { this.tipo = valor; }
    public RegraStatusEnum getStatus_servico() { return status_servico; }
    public void setStatus_servico(RegraStatusEnum valor) { this.status_servico = valor; }

    public BigDecimal getValor_sessao() {
        return valor_sessao;
    }

    public void setValor_sessao(BigDecimal valor_sessao) {
        this.valor_sessao = valor_sessao;
    }
}
