package projeto.fisio4life.fenix.Dto.sessaoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class SessaoRequestDto {
    @NotNull
    private Integer idAgendamento;
    @NotNull
    private LocalDateTime dataSessao;
    @NotBlank
    @Size(max = 255)
    private String descricao;
    @NotBlank
    @Size(max = 255)
    private String observacao;

    public SessaoRequestDto() {
    }

    public SessaoRequestDto(Integer idAgendamento, LocalDateTime dataSessao, String descricao, String observacao) {
        this.idAgendamento = idAgendamento;
        this.dataSessao = dataSessao;
        this.descricao = descricao;
        this.observacao = observacao;
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
