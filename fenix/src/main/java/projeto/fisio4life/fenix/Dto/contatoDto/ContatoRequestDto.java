package projeto.fisio4life.fenix.Dto.contatoDto;

import jakarta.validation.constraints.*;

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

    public ContatoRequestDto() {}

    public String getTelefone() { return telefone; }
    public void setTelefone(String valor) { this.telefone = valor; }

    public String getCelular() { return celular; }
    public void setCelular(String valor) { this.celular = valor; }

    public String getEmail() { return email; }
    public void setEmail(String valor) { this.email = valor; }

}
