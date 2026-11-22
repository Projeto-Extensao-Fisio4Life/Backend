package projeto.fisio4life.fenix.Dto.authDto;

import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Enum.StatusUsuarioEnum;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AuthResponseDto {


    private Integer idUsuario;
    private Integer idEndereco;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String celular;
    private String telefone;
    private TipoUsuarioEnum tipoUsuario;
    private LocalDateTime dataCadastro;
    private StatusUsuarioEnum statusUsuario;
    private String tokenJwt;

    public AuthResponseDto(Integer idUsuario, Integer idEndereco, String nome, LocalDate dataNascimento, String email, String celular, String telefone, TipoUsuarioEnum tipoUsuario, LocalDateTime dataCadastro, StatusUsuarioEnum statusUsuario, String tokenJwt) {
        this.idUsuario = idUsuario;
        this.idEndereco = idEndereco;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.celular = celular;
        this.telefone = telefone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public StatusUsuarioEnum getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(StatusUsuarioEnum statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public String getTokenJwt() {
        return tokenJwt;
    }

    public void setTokenJwt(String tokenJwt) {
        this.tokenJwt = tokenJwt;
    }
}
