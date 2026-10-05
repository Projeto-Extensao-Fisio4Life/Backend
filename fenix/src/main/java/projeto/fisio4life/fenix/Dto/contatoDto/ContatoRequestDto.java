package projeto.fisio4life.fenix.Dto.contatoDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContatoRequestDto {

    @NotBlank
    @Size(max = 14)
    private String telefone;

    @NotBlank
    @Size(max = 14)
    private String celular;

    @NotBlank
    @Email
    @Size(max = 45)
    private String email;

    public ContatoRequestDto() {
    }

    public ContatoRequestDto(String telefone, String celular, String email) {
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
