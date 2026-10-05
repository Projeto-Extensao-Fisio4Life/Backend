package projeto.fisio4life.fenix.Dto.usuarioDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioResponseDto {

    private Integer idUsuario;
    private Integer idEndereco;
    private Integer idContato;
    private String nome;
    private LocalDate dataNascimento;
    private String tipoUsuario;
    private LocalDateTime dataCadastro;
    private Integer statusUsuario;

    public UsuarioResponseDto() {
    }

    public UsuarioResponseDto(Integer idUsuario, Integer idEndereco, Integer idContato, String nome, LocalDate dataNascimento, String tipoUsuario, LocalDateTime dataCadastro, Integer statusUsuario) {
        this.idUsuario = idUsuario;
        this.idEndereco = idEndereco;
        this.idContato = idContato;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.tipoUsuario = tipoUsuario;
        this.dataCadastro = dataCadastro;
        this.statusUsuario = statusUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(Integer statusUsuario) {
        this.statusUsuario = statusUsuario;
    }
}
