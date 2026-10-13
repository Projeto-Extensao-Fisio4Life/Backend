package projeto.fisio4life.fenix.Dto.evolucaoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 255)
    private String observacao;

    public EvolucaoRequestDto() {
    }

    public EvolucaoRequestDto(Integer idSessao, Integer idProntuario, LocalDateTime dataEvolucao, String conduta, String avaliacao, String observacao) {
        this.idSessao = idSessao;
        this.idProntuario = idProntuario;
        this.dataEvolucao = dataEvolucao;
        this.conduta = conduta;
        this.avaliacao = avaliacao;
        this.observacao = observacao;
    }

    public Integer getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public Integer getIdProntuario() {
        return idProntuario;
    }

    public void setIdProntuario(Integer idProntuario) {
        this.idProntuario = idProntuario;
    }

    public LocalDateTime getDataEvolucao() {
        return dataEvolucao;
    }

    public void setDataEvolucao(LocalDateTime dataEvolucao) {
        this.dataEvolucao = dataEvolucao;
    }

    public String getConduta() {
        return conduta;
    }

    public void setConduta(String conduta) {
        this.conduta = conduta;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
