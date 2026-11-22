package projeto.fisio4life.fenix.Dto.pacienteDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioRequestDto;

public class PacienteRequestDto {

    @Valid
    @NotNull
    private UsuarioRequestDto usuario;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private Boolean permiteAtendimentoGrupo;

    public PacienteRequestDto() {}

    public UsuarioRequestDto getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioRequestDto usuario) {
        this.usuario = usuario;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String valor) { this.cpf = valor; }

    public Boolean getPermiteAtendimentoGrupo() { return permiteAtendimentoGrupo; }
    public void setPermiteAtendimentoGrupo(Boolean valor) { this.permiteAtendimentoGrupo = valor; }

}
