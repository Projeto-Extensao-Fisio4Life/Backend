package projeto.fisio4life.fenix.Dto.evolucaoDto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class EvolucaoRequestDto {

    @NotNull
    private Integer idSessao;

    @NotNull
    private Integer idProntuario;

    @NotNull
    private LocalDateTime dataEvolucao;

    @NotBlank
    @Size(max = 255)
    private String conduta;

    @NotBlank
    @Size(max = 255)
    private String avaliacao;

    @NotBlank
    @Size(max = 255)
    private String observacao;

    public EvolucaoRequestDto() {}

    public Integer getIdSessao() { return idSessao; }
    public void setIdSessao(Integer valor) { this.idSessao = valor; }

    public Integer getIdProntuario() { return idProntuario; }
    public void setIdProntuario(Integer valor) { this.idProntuario = valor; }

    public LocalDateTime getDataEvolucao() { return dataEvolucao; }
    public void setDataEvolucao(LocalDateTime valor) { this.dataEvolucao = valor; }

    public String getConduta() { return conduta; }
    public void setConduta(String valor) { this.conduta = valor; }

    public String getAvaliacao() { return avaliacao; }
    public void setAvaliacao(String valor) { this.avaliacao = valor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String valor) { this.observacao = valor; }

}
