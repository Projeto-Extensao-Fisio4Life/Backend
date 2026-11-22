package projeto.fisio4life.fenix.Dto.pacienteDto;

import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioResponseDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PacienteResponseDto {

    private Integer idPaciente;

    private UsuarioResponseDto usuario;

    private String cpf;

    private Boolean permiteAtendimentoGrupo;

    public PacienteResponseDto() {}

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer valor) { this.idPaciente = valor; }

    public UsuarioResponseDto getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioResponseDto usuario) {
        this.usuario = usuario;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String valor) { this.cpf = valor; }

    public Boolean getPermiteAtendimentoGrupo() { return permiteAtendimentoGrupo; }
    public void setPermiteAtendimentoGrupo(Boolean valor) { this.permiteAtendimentoGrupo = valor; }

}
