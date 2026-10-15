package projeto.fisio4life.fenix.Dto.pacienteDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PacienteResponseDto {

    private Integer idPaciente;

    private Integer idUsuario;

    private String cpf;

    private Boolean permiteAtendimentoGrupo;

    public PacienteResponseDto() {}

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer valor) { this.idPaciente = valor; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer valor) { this.idUsuario = valor; }

    public String getCpf() { return cpf; }
    public void setCpf(String valor) { this.cpf = valor; }

    public Boolean getPermiteAtendimentoGrupo() { return permiteAtendimentoGrupo; }
    public void setPermiteAtendimentoGrupo(Boolean valor) { this.permiteAtendimentoGrupo = valor; }

}
