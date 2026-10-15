package projeto.fisio4life.fenix.Dto.escalaDto;

import jakarta.validation.constraints.*;

public class EscalaRequestDto {

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotBlank
    @Size(max = 45)
    private String descricao;

    public EscalaRequestDto() {}

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String valor) { this.descricao = valor; }

}
