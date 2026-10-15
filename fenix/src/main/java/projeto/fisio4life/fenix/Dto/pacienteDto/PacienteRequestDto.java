package projeto.fisio4life.fenix.Dto.pacienteDto;

import jakarta.validation.constraints.*;

public class PacienteRequestDto {

    @NotNull
    private Integer idUsuario;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private Boolean permiteAtendimentoGrupo;

    public PacienteRequestDto() {}

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer valor) { this.idUsuario = valor; }

    public String getCpf() { return cpf; }
    public void setCpf(String valor) { this.cpf = valor; }

    public Boolean getPermiteAtendimentoGrupo() { return permiteAtendimentoGrupo; }
    public void setPermiteAtendimentoGrupo(Boolean valor) { this.permiteAtendimentoGrupo = valor; }

}
