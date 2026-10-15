package projeto.fisio4life.fenix.Dto.escalaPerguntasDto;

import jakarta.validation.constraints.*;

public class EscalaPerguntasRequestDto {

    @NotNull
    private Integer idEscala;

    @NotBlank
    @Size(max = 45)
    private String tituloPergunta;

    @NotBlank
    @Size(max = 45)
    private String tipoDado;

    public EscalaPerguntasRequestDto() {}

    public Integer getIdEscala() { return idEscala; }
    public void setIdEscala(Integer valor) { this.idEscala = valor; }

    public String getTituloPergunta() { return tituloPergunta; }
    public void setTituloPergunta(String valor) { this.tituloPergunta = valor; }

    public String getTipoDado() { return tipoDado; }
    public void setTipoDado(String valor) { this.tipoDado = valor; }

}
