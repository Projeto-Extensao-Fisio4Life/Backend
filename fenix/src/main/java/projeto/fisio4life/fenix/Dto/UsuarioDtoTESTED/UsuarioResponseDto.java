package projeto.fisio4life.fenix.Dto.UsuarioDtoTESTED;

import projeto.fisio4life.fenix.Entity.Contato;
import projeto.fisio4life.fenix.Entity.Endereco;

import java.time.LocalDateTime;

public class UsuarioResponseDto {
    private Integer id_usuario;
    private Endereco endereco;
    private Contato contato;
    private String nome;
    private String tipo_usuario;
    private LocalDateTime data_cadastro;
    private Integer status_usuario;

    public UsuarioResponseDto() {
    }

    public UsuarioResponseDto(Integer id_usuario, Endereco endereco, Contato contato, String nome, String senha, String tipo_usuario, LocalDateTime data_cadastro, Integer status_usuario) {
        this.id_usuario = id_usuario;
        this.endereco = endereco;
        this.contato = contato;
        this.nome = nome;
        this.tipo_usuario = tipo_usuario;
        this.data_cadastro = data_cadastro;
        this.status_usuario = status_usuario;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Integer getStatus_usuario() {
        return status_usuario;
    }

    public void setStatus_usuario(Integer status_usuario) {
        this.status_usuario = status_usuario;
    }
}
