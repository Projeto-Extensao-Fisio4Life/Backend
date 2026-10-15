package projeto.fisio4life.fenix.Dto.escalaProntuarioDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EscalaProntuarioResponseDto {

    private Integer idEscalaProntuario;

    private Integer idProntuario;

    private Integer idEscala;

    private LocalDateTime dataResposta;

    public EscalaProntuarioResponseDto() {}

    public Integer getIdEscalaProntuario() { return idEscalaProntuario; }
    public void setIdEscalaProntuario(Integer valor) { this.idEscalaProntuario = valor; }

    public Integer getIdProntuario() { return idProntuario; }
    public void setIdProntuario(Integer valor) { this.idProntuario = valor; }

    public Integer getIdEscala() { return idEscala; }
    public void setIdEscala(Integer valor) { this.idEscala = valor; }

    public LocalDateTime getDataResposta() { return dataResposta; }
    public void setDataResposta(LocalDateTime valor) { this.dataResposta = valor; }

}
