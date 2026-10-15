package projeto.fisio4life.fenix.Dto.pagamentoDto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class PagamentoRequestDto {

    private Integer idPaciente;

    private Integer idServico;

    @DecimalMin(value = "0.00")
    private BigDecimal valor;

    @Size(max = 45)
    private String formaPagamento;

    private Integer statusPagamento;

    @Size(max = 45)
    private String dataConcluida;

    public PagamentoRequestDto() {}

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer valor) { this.idPaciente = valor; }

    public Integer getIdServico() { return idServico; }
    public void setIdServico(Integer valor) { this.idServico = valor; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String valor) { this.formaPagamento = valor; }

    public Integer getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(Integer valor) { this.statusPagamento = valor; }

    public String getDataConcluida() { return dataConcluida; }
    public void setDataConcluida(String valor) { this.dataConcluida = valor; }

}
