package projeto.fisio4life.fenix.Dto.authDto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AuthRequestDto {

    @NotNull
    @NotEmpty
    private String usuario;

    @NotNull
    @NotEmpty
    private String senha;

    public AuthRequestDto(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
