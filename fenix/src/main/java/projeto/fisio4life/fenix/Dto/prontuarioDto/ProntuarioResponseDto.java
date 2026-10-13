package projeto.fisio4life.fenix.Dto.prontuarioDto;

import java.time.LocalDateTime;

public class ProntuarioResponseDto {

    private Integer idProntuario;
    private Integer idPaciente;
    private LocalDateTime dataAbertura;
    private Boolean statusProntuario;
    private String observacaoInicial;

    public ProntuarioResponseDto() {
    }

    public ProntuarioResponseDto(Integer idPaciente, Integer idProntuario, LocalDateTime dataAbertura, Boolean statusProntuario, String observacaoInicial) {
        this.idPaciente = idPaciente;
        this.idProntuario = idProntuario;
        this.dataAbertura = dataAbertura;
        this.statusProntuario = statusProntuario;
        this.observacaoInicial = observacaoInicial;
    }

    public Integer getIdProntuario() {
        return idProntuario;
    }

    public void setIdProntuario(Integer idProntuario) {
        this.idProntuario = idProntuario;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Boolean getStatusProntuario() {
        return statusProntuario;
    }

    public void setStatusProntuario(Boolean statusProntuario) {
        this.statusProntuario = statusProntuario;
    }

    public String getObservacaoInicial() {
        return observacaoInicial;
    }

    public void setObservacaoInicial(String observacaoInicial) {
        this.observacaoInicial = observacaoInicial;
    }
}
