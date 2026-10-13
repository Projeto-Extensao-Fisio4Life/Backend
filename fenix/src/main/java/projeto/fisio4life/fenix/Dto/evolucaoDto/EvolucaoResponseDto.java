package projeto.fisio4life.fenix.Dto.evolucaoDto;

import java.time.LocalDateTime;

public class EvolucaoResponseDto {

    private Integer idEvolucao;
    private Integer idSessao;
    private Integer idProntuario;
    private LocalDateTime dataEvolucao;
    private String conduta;
    private String avaliacao;
    private String observacao;

    public EvolucaoResponseDto() {
    }

    public EvolucaoResponseDto(Integer idEvolucao, Integer idSessao, Integer idProntuario, LocalDateTime dataEvolucao, String conduta, String avaliacao, String observacao) {
        this.idEvolucao = idEvolucao;
        this.idSessao = idSessao;
        this.idProntuario = idProntuario;
        this.dataEvolucao = dataEvolucao;
        this.conduta = conduta;
        this.avaliacao = avaliacao;
        this.observacao = observacao;
    }

    public Integer getIdEvolucao() {
        return idEvolucao;
    }

    public void setIdEvolucao(Integer idEvolucao) {
        this.idEvolucao = idEvolucao;
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
