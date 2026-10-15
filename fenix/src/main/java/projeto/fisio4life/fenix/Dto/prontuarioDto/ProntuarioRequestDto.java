package projeto.fisio4life.fenix.Dto.prontuarioDto;

import jakarta.validation.constraints.*;

public class ProntuarioRequestDto {

    @NotNull
    private Integer idPaciente;

    @NotNull
    private Integer statusProntuario;

    @Size(max = 255)
    private String observacaoInicial;

    public ProntuarioRequestDto() {}

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer valor) { this.idPaciente = valor; }

    public Integer getStatusProntuario() { return statusProntuario; }
    public void setStatusProntuario(Integer valor) { this.statusProntuario = valor; }

    public String getObservacaoInicial() { return observacaoInicial; }
    public void setObservacaoInicial(String valor) { this.observacaoInicial = valor; }

}
