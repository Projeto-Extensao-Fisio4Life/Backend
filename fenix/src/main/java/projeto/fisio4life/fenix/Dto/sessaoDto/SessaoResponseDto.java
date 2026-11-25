package projeto.fisio4life.fenix.Dto.sessaoDto;

import projeto.fisio4life.fenix.Dto.agendamentoDto.AgendamentoResponseDto;
import projeto.fisio4life.fenix.Dto.servicoDto.ServicoResponseDto;
import projeto.fisio4life.fenix.Enum.StatusSessaoEnum;

import java.time.LocalDateTime;

public class SessaoResponseDto {

    private Integer idSessao;

    private AgendamentoResponseDto agendamento;

    private ServicoResponseDto servico;

    private LocalDateTime dataSessao;

    private String descricao;

    private String observacao;

    private StatusSessaoEnum status_sessao;

    private Boolean gera_cobranca;

    public SessaoResponseDto() {}

    public Integer getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public AgendamentoResponseDto getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(AgendamentoResponseDto agendamento) {
        this.agendamento = agendamento;
    }

    public ServicoResponseDto getServico() {
        return servico;
    }

    public void setServico(ServicoResponseDto servico) {
        this.servico = servico;
    }

    public LocalDateTime getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(LocalDateTime dataSessao) {
        this.dataSessao = dataSessao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusSessaoEnum getStatus_sessao() {
        return status_sessao;
    }

    public void setStatus_sessao(StatusSessaoEnum status_sessao) {
        this.status_sessao = status_sessao;
    }

    public Boolean getGera_cobranca() {
        return gera_cobranca;
    }

    public void setGera_cobranca(Boolean gera_cobranca) {
        this.gera_cobranca = gera_cobranca;
    }
}
