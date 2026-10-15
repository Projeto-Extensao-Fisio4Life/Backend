package projeto.fisio4life.fenix.Dto.enderecoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EnderecoResponseDto {

    private Integer idEndereco;

    private String logradouro;

    private Integer numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

    public EnderecoResponseDto() {}

    public Integer getIdEndereco() { return idEndereco; }
    public void setIdEndereco(Integer valor) { this.idEndereco = valor; }

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
