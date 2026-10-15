package projeto.fisio4life.fenix.Dto.servicoDto;

import jakarta.validation.constraints.*;

public class ServicoRequestDto {

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotNull
    @Min(1)
    private Integer quantidadeSessoes;

    @NotBlank
    @Size(max = 20)
    private String tipo;

    @NotNull
    private Integer statusServico;

    public ServicoRequestDto() {}

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public Integer getQuantidadeSessoes() { return quantidadeSessoes; }
    public void setQuantidadeSessoes(Integer valor) { this.quantidadeSessoes = valor; }

    public String getTipo() { return tipo; }
    public void setTipo(String valor) { this.tipo = valor; }

    public Integer getStatusServico() { return statusServico; }
    public void setStatusServico(Integer valor) { this.statusServico = valor; }

}
