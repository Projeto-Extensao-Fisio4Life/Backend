package projeto.fisio4life.fenix.Dto.usuarioDto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import projeto.fisio4life.fenix.Entity.Contato;
import projeto.fisio4life.fenix.Entity.Endereco;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioRequestDto {

    @Valid
    @NotNull
    private  Integer idEndereco;

    @Valid
    @NotNull
    private Integer idContato;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @Past
    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private LocalDateTime dataCadastro;

    @NotBlank
    @Size(max = 60)
    private String senha;

    @NotBlank
    @Size(max = 45)
    private String tipoUsuario;

    @NotNull
    private Integer statusUsuario;

    public UsuarioRequestDto() {
    }

    public UsuarioRequestDto(Integer idEndereco, Integer idContato, String nome, LocalDate dataNascimento, LocalDateTime dataCadastro, String senha, String tipoUsuario, Integer statusUsuario) {
        this.idEndereco = idEndereco;
        this.idContato = idContato;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.statusUsuario = statusUsuario;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(Integer statusUsuario) {
        this.statusUsuario = statusUsuario;
    }
}
