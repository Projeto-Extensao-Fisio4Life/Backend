package projeto.fisio4life.fenix.Dto.evolucaoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EvolucaoResponseDto {

    private Integer idEvolucao;

    private Integer idSessao;

    private Integer idProntuario;

    private LocalDateTime dataEvolucao;

    private String conduta;

    private String avaliacao;

    private String observacao;

    public EvolucaoResponseDto() {}

    public Integer getIdEvolucao() { return idEvolucao; }
    public void setIdEvolucao(Integer valor) { this.idEvolucao = valor; }

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
