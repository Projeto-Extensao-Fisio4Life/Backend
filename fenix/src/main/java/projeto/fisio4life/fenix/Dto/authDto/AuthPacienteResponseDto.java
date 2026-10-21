package projeto.fisio4life.fenix.Dto.authDto;

import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AuthPacienteResponseDto extends AuthResponseDto {

    private Integer idPaciente;
    private String cpf;
    private Boolean permiteAtendimentoGrupo;
    

    public AuthPacienteResponseDto(
            Integer idUsuario,
            Integer idEndereco,
            Integer idContato,
            String nome,
            LocalDate dataNascimento,
            TipoUsuarioEnum tipoUsuario,
            LocalDateTime dataCadastro,
            Integer statusUsuario,
            String tokenJwt,
            Integer idPaciente,
            String cpf,
            Boolean permiteAtendimentoGrupo
    ) {
        super(idUsuario, idEndereco, idContato, nome, dataNascimento, tipoUsuario, dataCadastro, statusUsuario, tokenJwt);
        this.idPaciente = idPaciente;
        this.cpf = cpf;
        this.permiteAtendimentoGrupo = permiteAtendimentoGrupo;
    }

    public AuthPacienteResponseDto(Integer idUsuario, Integer idEndereco, Integer idContato, String nome, LocalDate dataNascimento, TipoUsuarioEnum tipoUsuario, LocalDateTime dataCadastro, Integer statusUsuario, String tokenJwt) {
        super(idUsuario, idEndereco, idContato, nome, dataNascimento, tipoUsuario, dataCadastro, statusUsuario, tokenJwt);
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
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
