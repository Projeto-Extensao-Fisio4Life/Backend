package projeto.fisio4life.fenix.Dto.servicoDto;

import jakarta.validation.constraints.*;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;

import java.math.BigDecimal;

public class ServicoRequestDto {

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotNull
    @Min(1)
    private Integer quantidadeSessoes;

    @NotBlank
    @Size(max = 20)
    private String tipo;

    @NotNull
    private RegraStatusEnum statusServico;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal valorSessao;


    public ServicoRequestDto() {}

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public Integer getQuantidadeSessoes() { return quantidadeSessoes; }
    public void setQuantidadeSessoes(Integer valor) { this.quantidadeSessoes = valor; }

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
