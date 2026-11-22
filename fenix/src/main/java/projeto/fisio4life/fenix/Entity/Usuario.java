package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;
import org.hibernate.type.descriptor.jdbc.TinyIntAsSmallIntJdbcType;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    @ManyToOne
    @JoinColumn(name = "fk_endereco")
    private Endereco endereco;

    private String nome;
    private String senha;
    private String email;
    private String celular;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipo_usuario;
    private LocalDate data_nascimento;
    private LocalDateTime data_cadastro;
    private Integer status_usuario;

    public Usuario() {
    }

    public Usuario(Integer id_usuario, Endereco endereco, String nome, String senha, String email, String celular, String telefone, TipoUsuarioEnum tipo_usuario, LocalDate data_nascimento, LocalDateTime data_cadastro, Integer status_usuario) {
        this.id_usuario = id_usuario;
        this.endereco = endereco;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.celular = celular;
        this.telefone = telefone;
        this.tipo_usuario = tipo_usuario;
        this.data_nascimento = data_nascimento;
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

    public TipoUsuarioEnum getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(TipoUsuarioEnum tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
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
