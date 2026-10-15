package projeto.fisio4life.fenix.Dto.escalaRespostasDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EscalaRespostasResponseDto {

    private Integer idEscalaRespostas;

    private Integer idEscalaProntuario;

    private Integer idEscalaPerguntas;

    private String valorResposta;

    public EscalaRespostasResponseDto() {}

    public Integer getIdEscalaRespostas() { return idEscalaRespostas; }
    public void setIdEscalaRespostas(Integer valor) { this.idEscalaRespostas = valor; }

    public Integer getIdEscalaProntuario() { return idEscalaProntuario; }
    public void setIdEscalaProntuario(Integer valor) { this.idEscalaProntuario = valor; }

    public Integer getIdEscalaPerguntas() { return idEscalaPerguntas; }
    public void setIdEscalaPerguntas(Integer valor) { this.idEscalaPerguntas = valor; }

    public String getValorResposta() { return valorResposta; }
    public void setValorResposta(String valor) { this.valorResposta = valor; }

}
