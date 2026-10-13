package projeto.fisio4life.fenix.Dto.servicoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ServicoRequestDto {
    @NotNull
    private Integer idClinica;
    @NotBlank
    @Size(max = 45)
    private String nome;
    @NotNull
    private Integer quantidadeSessoes;
    @NotNull
    private Double valor;
    @NotBlank
    @Size(max = 20)
    private String tipo;

    public ServicoRequestDto() {
    }

    public ServicoRequestDto(Integer idClinica, String nome, Integer quantidadeSessoes, Double valor, String tipo) {
        this.idClinica = idClinica;
        this.nome = nome;
        this.quantidadeSessoes = quantidadeSessoes;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Integer getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Integer idClinica) {
        this.idClinica = idClinica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeSessoes() {
        return quantidadeSessoes;
    }

    public void setQuantidadeSessoes(Integer quantidadeSessoes) {
        this.quantidadeSessoes = quantidadeSessoes;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
