package projeto.fisio4life.fenix.Dto.clinicaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClinicaRequestDto {

    @NotNull
    private Integer idContato;

    @NotNull
    private Integer idEndereco;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Size(max = 14)
    private String cnpj;

    public ClinicaRequestDto() {
    }

    public ClinicaRequestDto(Integer idContato, Integer idEndereco, String nome, String cnpj) {
        this.idContato = idContato;
        this.idEndereco = idEndereco;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

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
}
