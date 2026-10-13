package projeto.fisio4life.fenix.Dto.prontuarioDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class ProntuarioRequestDto {

    @NotNull
    private Integer idPaciente;
    private LocalDateTime dataAbertura;
    @NotNull
    private Boolean statusProntuario;
    @Size(max = 255)
    private String observacaoInicial;

    public ProntuarioRequestDto() {
    }

    public ProntuarioRequestDto(Integer idPaciente, LocalDateTime dataAbertura, Boolean statusProntuario, String observacaoInicial) {
        this.idPaciente = idPaciente;
        this.dataAbertura = dataAbertura;
        this.statusProntuario = statusProntuario;
        this.observacaoInicial = observacaoInicial;
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
