package projeto.fisio4life.fenix.Dto.pagamentoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PagamentoResponseDto {

    private Integer idPagamento;

    private Integer idPaciente;

    private Integer idServico;

    private BigDecimal valor;

    private String formaPagamento;

    private Integer statusPagamento;

    private String dataConcluida;

    public PagamentoResponseDto() {}

    public Integer getIdPagamento() { return idPagamento; }
    public void setIdPagamento(Integer valor) { this.idPagamento = valor; }

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
