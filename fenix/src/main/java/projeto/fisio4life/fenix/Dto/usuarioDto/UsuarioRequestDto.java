package projeto.fisio4life.fenix.Dto.usuarioDto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import java.time.LocalDate;

public class UsuarioRequestDto {

    @NotNull
    private Integer idEndereco;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @NotBlank
    @Size(min = 8, max = 60)
    private String senha;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 14, max = 14)
    private String celular;

    @Size(min = 14, max = 14)
    private String telefone;

    @NotBlank
    @Size(min = 8, max = 255)
    private String tipoUsuario;

    @NotNull
    private Integer statusUsuario;

    public UsuarioRequestDto() {}

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
