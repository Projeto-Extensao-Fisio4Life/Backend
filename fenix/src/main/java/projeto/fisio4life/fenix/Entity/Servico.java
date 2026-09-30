package projeto.fisio4life.fenix.Entity;


import jakarta.persistence.*;

@Entity
@Table
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_servico;
    @ManyToOne
    @JoinColumn(name = "fk_clinica")
    private Clinica clinica;
    private String nome;
    private Integer quantidade_sessoes;
    private Double valor;
    private String tipo;

    public Servico() {
    }

    public Servico(Long id_servico, Clinica clinica, String nome, Integer quantidade_sessoes, Double valor, String tipo) {
        this.id_servico = id_servico;
        this.clinica = clinica;
        this.nome = nome;
        this.quantidade_sessoes = quantidade_sessoes;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Long getId_servico() {
        return id_servico;
    }

    public void setId_servico(Long id_servico) {
        this.id_servico = id_servico;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade_sessoes() {
        return quantidade_sessoes;
    }

    public void setQuantidade_sessoes(Integer quantidade_sessoes) {
        this.quantidade_sessoes = quantidade_sessoes;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
