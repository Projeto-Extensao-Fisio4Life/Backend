package projeto.fisio4life.fenix.Dto.pacienteDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PacienteRequestDto {

    @NotNull
    private Integer idUsuario;

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotNull
    private Boolean permiteAtendimentoGrupo;

    public PacienteRequestDto() {
    }

    public PacienteRequestDto(Integer idUsuario, String cpf, Boolean permiteAtendimentoGrupo) {
        this.idUsuario = idUsuario;
        this.cpf = cpf;
        this.permiteAtendimentoGrupo = permiteAtendimentoGrupo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getPermiteAtendimentoGrupo() {
        return permiteAtendimentoGrupo;
    }

    public void setPermiteAtendimentoGrupo(Boolean permiteAtendimentoGrupo) {
        this.permiteAtendimentoGrupo = permiteAtendimentoGrupo;
    }
}
