package projeto.fisio4life.fenix.Dto.enderecoDto;

import jakarta.validation.constraints.*;

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
    @Size(min = 2, max = 2)
    private String estado;

    @NotBlank
    @Size(max = 45)
    private String cep;

    public EnderecoRequestDto() {}

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String valor) { this.logradouro = valor; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer valor) { this.numero = valor; }

    public String getBairro() { return bairro; }
    public void setBairro(String valor) { this.bairro = valor; }

    public String getCidade() { return cidade; }
    public void setCidade(String valor) { this.cidade = valor; }

    public String getEstado() { return estado; }
    public void setEstado(String valor) { this.estado = valor; }

    public String getCep() { return cep; }
    public void setCep(String valor) { this.cep = valor; }

}
