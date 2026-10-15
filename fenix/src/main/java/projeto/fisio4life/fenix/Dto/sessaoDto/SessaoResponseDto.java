package projeto.fisio4life.fenix.Dto.sessaoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SessaoResponseDto {

    private Integer idSessao;

    private Integer idAgendamento;

    private Integer idServico;

    private LocalDateTime dataSessao;

    private String descricao;

    private String observacao;

    public SessaoResponseDto() {}

    public Integer getIdSessao() { return idSessao; }
    public void setIdSessao(Integer valor) { this.idSessao = valor; }

    public Integer getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(Integer valor) { this.idAgendamento = valor; }

    public Integer getIdServico() { return idServico; }
    public void setIdServico(Integer valor) { this.idServico = valor; }

    public LocalDateTime getDataSessao() { return dataSessao; }
    public void setDataSessao(LocalDateTime valor) { this.dataSessao = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String valor) { this.descricao = valor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String valor) { this.observacao = valor; }

}
