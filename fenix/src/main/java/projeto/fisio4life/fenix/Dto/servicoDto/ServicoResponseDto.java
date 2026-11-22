package projeto.fisio4life.fenix.Dto.servicoDto;

import projeto.fisio4life.fenix.Enum.RegraStatusEnum;

import java.math.BigDecimal;

public class ServicoResponseDto {

    private Integer idServico;

    private String nome;

    private Integer quantidade_sessoes;

    private String tipo;

    private RegraStatusEnum statusServico;

    private BigDecimal valorSessao;

    public ServicoResponseDto() {}

    public Integer getIdServico() { return idServico; }
    public void setIdServico(Integer valor) { this.idServico = valor; }

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public Integer getQuantidade_sessoes() { return quantidade_sessoes; }
    public void setQuantidade_sessoes(Integer valor) { this.quantidade_sessoes = valor; }

    public String getTipo() { return tipo; }
    public void setTipo(String valor) { this.tipo = valor; }

    public RegraStatusEnum getStatusServico() { return statusServico; }
    public void setStatusServico(RegraStatusEnum valor) { this.statusServico = valor; }

    public BigDecimal getValorSessao() {
        return valorSessao;
    }

    public void setValorSessao(BigDecimal valorSessao) {
        this.valorSessao = valorSessao;
    }
}
