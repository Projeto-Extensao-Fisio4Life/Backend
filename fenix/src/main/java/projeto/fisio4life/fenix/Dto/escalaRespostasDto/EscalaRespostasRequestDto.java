package projeto.fisio4life.fenix.Dto.escalaRespostasDto;

import jakarta.validation.constraints.*;

public class EscalaRespostasRequestDto {

    @NotNull
    private Integer idEscalaProntuario;

    @NotNull
    private Integer idEscalaPerguntas;

    @NotBlank
    @Size(max = 45)
    private String valorResposta;

    public EscalaRespostasRequestDto() {}

    public Integer getIdEscalaProntuario() { return idEscalaProntuario; }
    public void setIdEscalaProntuario(Integer valor) { this.idEscalaProntuario = valor; }

    public Integer getIdEscalaPerguntas() { return idEscalaPerguntas; }
    public void setIdEscalaPerguntas(Integer valor) { this.idEscalaPerguntas = valor; }

    public String getValorResposta() { return valorResposta; }
    public void setValorResposta(String valor) { this.valorResposta = valor; }

}
