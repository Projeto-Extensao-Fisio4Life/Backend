package projeto.fisio4life.fenix.Dto.clinicaDto;

import jakarta.validation.constraints.*;

public class ClinicaRequestDto {



    @NotNull
    private Integer idEndereco;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{14}")
    @Size(min = 14, max = 14)
    private String cnpj;

    @NotBlank
    @Email
    @Size(max = 45)
    private String email;

    @NotBlank
    @Size(min = 14, max = 14)
    private String celular;

    @Size(min = 14, max = 14)
    private String telefone;


    public ClinicaRequestDto() {}

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
}
