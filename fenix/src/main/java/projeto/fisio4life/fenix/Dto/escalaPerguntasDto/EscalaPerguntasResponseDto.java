package projeto.fisio4life.fenix.Dto.escalaPerguntasDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EscalaPerguntasResponseDto {

    private Integer idEscalaPerguntas;

    private Integer idEscala;

    private String tituloPergunta;

    private String tipoDado;

    public EscalaPerguntasResponseDto() {}

    public Integer getIdEscalaPerguntas() { return idEscalaPerguntas; }
    public void setIdEscalaPerguntas(Integer valor) { this.idEscalaPerguntas = valor; }

    public Integer getIdEscala() { return idEscala; }
    public void setIdEscala(Integer valor) { this.idEscala = valor; }

    public String getTituloPergunta() { return tituloPergunta; }
    public void setTituloPergunta(String valor) { this.tituloPergunta = valor; }

    public String getTipoDado() { return tipoDado; }
    public void setTipoDado(String valor) { this.tipoDado = valor; }

}
