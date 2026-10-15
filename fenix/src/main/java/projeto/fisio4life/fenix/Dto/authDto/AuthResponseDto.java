package projeto.fisio4life.fenix.Dto.authDto;

import projeto.fisio4life.fenix.Entity.Contato;
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AuthResponseDto {


    private Integer idUsuario;
    private Integer idEndereco;
    private Integer idContato;
    private String nome;
    private LocalDate dataNascimento;
    private TipoUsuarioEnum tipoUsuario;
    private LocalDateTime dataCadastro;
    private Integer statusUsuario;
    private long tokenJwt;

    public AuthResponseDto(Integer idUsuario, Integer idEndereco, Integer idContato, String nome, LocalDate dataNascimento, TipoUsuarioEnum tipoUsuario, LocalDateTime dataCadastro, Integer statusUsuario, long tokenJwt) {
        this.idUsuario = idUsuario;
        this.idEndereco = idEndereco;
        this.idContato = idContato;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.tipoUsuario = tipoUsuario;
        this.dataCadastro = dataCadastro;
        this.statusUsuario = statusUsuario;
        this.tokenJwt = tokenJwt;
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

    public TipoUsuarioEnum getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
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

    public long getTokenJwt() {
        return tokenJwt;
    }

    public void setTokenJwt(long tokenJwt) {
        this.tokenJwt = tokenJwt;
    }
}
