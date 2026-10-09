package projeto.fisio4life.fenix.Dto.pacienteDto;

public class PacienteResponseDto {

    private Integer idPaciente;
    private Integer idUsuario;
    private String cpf;
    private Boolean permiteAtendimentoGrupo;

    public PacienteResponseDto() {
    }

    public PacienteResponseDto(Integer idPaciente, Integer idUsuario, String cpf, Boolean permiteAtendimentoGrupo) {
        this.idPaciente = idPaciente;
        this.idUsuario = idUsuario;
        this.cpf = cpf;
        this.permiteAtendimentoGrupo = permiteAtendimentoGrupo;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
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
