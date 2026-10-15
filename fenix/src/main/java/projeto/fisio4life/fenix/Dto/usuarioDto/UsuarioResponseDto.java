package projeto.fisio4life.fenix.Dto.usuarioDto;

import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UsuarioResponseDto {

    private Integer idUsuario;

    private Integer idEndereco;

    private Integer idContato;

    private String nome;

    private LocalDate dataNascimento;

    private TipoUsuarioEnum tipoUsuario;

    private LocalDateTime dataCadastro;

    private Integer statusUsuario;

    public UsuarioResponseDto() {}

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer valor) { this.idUsuario = valor; }

    public Integer getIdEndereco() { return idEndereco; }
    public void setIdEndereco(Integer valor) { this.idEndereco = valor; }

    public Integer getIdContato() { return idContato; }
    public void setIdContato(Integer valor) { this.idContato = valor; }

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate valor) { this.dataNascimento = valor; }

    public TipoUsuarioEnum getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime valor) { this.dataCadastro = valor; }

    public Integer getStatusUsuario() { return statusUsuario; }
    public void setStatusUsuario(Integer valor) { this.statusUsuario = valor; }

}
