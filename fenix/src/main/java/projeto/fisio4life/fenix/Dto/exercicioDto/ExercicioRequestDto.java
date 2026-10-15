package projeto.fisio4life.fenix.Dto.exercicioDto;

import jakarta.validation.constraints.*;

public class ExercicioRequestDto {

    @NotBlank
    @Size(max = 45)
    private String categoria;

    @NotBlank
    @Size(max = 45)
    private String nome;

    public ExercicioRequestDto() {}

    public String getCategoria() { return categoria; }
    public void setCategoria(String valor) { this.categoria = valor; }

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

}
