package projeto.fisio4life.fenix.Dto.sessaoDto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class SessaoRequestDto {

    @NotNull
    private Integer idAgendamento;

    @NotNull
    private Integer idServico;

    @NotNull
    private LocalDateTime dataSessao;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotBlank
    @Size(max = 255)
    private String observacao;

    public SessaoRequestDto() {}

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
