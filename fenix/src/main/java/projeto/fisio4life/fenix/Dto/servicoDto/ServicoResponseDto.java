package projeto.fisio4life.fenix.Dto.servicoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServicoResponseDto {

    private Integer idServico;

    private String nome;

    private Integer quantidade_sessoes;

    private String tipo;

    private Integer statusServico;

    public ServicoResponseDto() {}

    public Integer getIdServico() { return idServico; }
    public void setIdServico(Integer valor) { this.idServico = valor; }

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public Integer getQuantidade_sessoes() { return quantidade_sessoes; }
    public void setQuantidade_sessoes(Integer valor) { this.quantidade_sessoes = valor; }

    public String getTipo() { return tipo; }
    public void setTipo(String valor) { this.tipo = valor; }

    public Integer getStatusServico() { return statusServico; }
    public void setStatusServico(Integer valor) { this.statusServico = valor; }

}
