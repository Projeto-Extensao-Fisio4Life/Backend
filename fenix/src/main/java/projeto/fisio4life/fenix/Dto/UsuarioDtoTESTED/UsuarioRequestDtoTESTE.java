package projeto.fisio4life.fenix.Dto.UsuarioDtoTESTED;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioRequestDtoTESTE {

    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    @Size(min = 4, message = "Número mínimo de caracteres não atendido")
    private String senha;
    @NotNull
    @NotBlank
    private String tipo_usuario;

    @NotNull
    @Past
    private LocalDate Data_nascimento;

    @NotNull
    private LocalDateTime data_cadastro;

    public UsuarioRequestDtoTESTE() {
    }


    public UsuarioRequestDtoTESTE(String nome, String senha, String tipo_usuario, LocalDate data_nascimento, LocalDateTime data_cadastro) {
        this.nome = nome;
        this.senha = senha;
        this.tipo_usuario = tipo_usuario;
        Data_nascimento = data_nascimento;
        this.data_cadastro = data_cadastro;
    }


    public LocalDate getData_nascimento() {
        return Data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        Data_nascimento = data_nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
}
