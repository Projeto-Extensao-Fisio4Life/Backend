package projeto.fisio4life.fenix.Dto.exercicioDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ExercicioResponseDto {

    private Integer idExercicio;

    private String categoria;

    private String nome;

    public ExercicioResponseDto() {}

    public Integer getIdExercicio() { return idExercicio; }
    public void setIdExercicio(Integer valor) { this.idExercicio = valor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String valor) { this.categoria = valor; }

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

}
