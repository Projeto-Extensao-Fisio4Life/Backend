package projeto.fisio4life.fenix.Dto.escalaProntuarioDto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class EscalaProntuarioRequestDto {

    @NotNull
    private Integer idProntuario;

    @NotNull
    private Integer idEscala;

    @NotNull
    private LocalDateTime dataResposta;

    public EscalaProntuarioRequestDto() {}

    public Integer getIdProntuario() { return idProntuario; }
    public void setIdProntuario(Integer valor) { this.idProntuario = valor; }

    public Integer getIdEscala() { return idEscala; }
    public void setIdEscala(Integer valor) { this.idEscala = valor; }

    public LocalDateTime getDataResposta() { return dataResposta; }
    public void setDataResposta(LocalDateTime valor) { this.dataResposta = valor; }

}
