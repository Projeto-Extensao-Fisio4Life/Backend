package projeto.fisio4life.fenix.Dto.sessaoDto;

import java.time.LocalDateTime;

public class SessaoResponseDto {

    private Integer idSessao;
    private Integer idAgendamento;
    private LocalDateTime dataSessao;
    private String descricao;
    private String observacao;

    public SessaoResponseDto() {
    }

    public SessaoResponseDto(Integer idSessao, Integer idAgendamento, LocalDateTime dataSessao, String descricao, String observacao) {
        this.idSessao = idSessao;
        this.idAgendamento = idAgendamento;
        this.dataSessao = dataSessao;
        this.descricao = descricao;
        this.observacao = observacao;
    }

    public Integer getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
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
}
