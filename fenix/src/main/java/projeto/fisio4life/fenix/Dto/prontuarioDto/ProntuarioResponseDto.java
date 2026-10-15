package projeto.fisio4life.fenix.Dto.prontuarioDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProntuarioResponseDto {

    private Integer idProntuario;

    private Integer idPaciente;

    private LocalDateTime dataAbertura;

    private Integer statusProntuario;

    private String observacaoInicial;

    public ProntuarioResponseDto() {}

    public Integer getIdProntuario() { return idProntuario; }
    public void setIdProntuario(Integer valor) { this.idProntuario = valor; }

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer valor) { this.idPaciente = valor; }

    public LocalDateTime getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDateTime valor) { this.dataAbertura = valor; }

    public Integer getStatusProntuario() { return statusProntuario; }
    public void setStatusProntuario(Integer valor) { this.statusProntuario = valor; }

    public String getObservacaoInicial() { return observacaoInicial; }
    public void setObservacaoInicial(String valor) { this.observacaoInicial = valor; }

}
