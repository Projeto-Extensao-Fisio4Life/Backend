package projeto.fisio4life.fenix.Dto.usuarioDto;

import jakarta.validation.constraints.*;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import java.time.LocalDate;

public class UsuarioRequestDto {

    @NotNull
    private Integer idEndereco;

    @NotNull
    private Integer idContato;

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
    @Size(max = 45)
    private TipoUsuarioEnum tipoUsuario;

    @NotNull
    private Integer statusUsuario;

    public UsuarioRequestDto() {}

    public Integer getIdEndereco() { return idEndereco; }
    public void setIdEndereco(Integer valor) { this.idEndereco = valor; }

    public Integer getIdContato() { return idContato; }
    public void setIdContato(Integer valor) { this.idContato = valor; }

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate valor) { this.dataNascimento = valor; }

    public String getSenha() { return senha; }
    public void setSenha(String valor) { this.senha = valor; }

    public TipoUsuarioEnum getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getStatusUsuario() { return statusUsuario; }
    public void setStatusUsuario(Integer valor) { this.statusUsuario = valor; }

}
