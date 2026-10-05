package projeto.fisio4life.fenix.Dto.enderecoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnderecoRequestDto {

    @NotBlank
    @Size(max = 45)
    private String logradouro;

    @NotNull
    private Integer numero;

    @NotBlank
    @Size(max = 45)
    private String bairro;

    @NotBlank
    @Size(max = 45)
    private String cidade;

    @NotBlank
    @Size(max = 2)
    private String estado;

    @NotBlank
    @Size(max = 45)
    private String cep;

    public EnderecoRequestDto() {
    }

    public EnderecoRequestDto(String logradouro, Integer numero, String bairro, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
