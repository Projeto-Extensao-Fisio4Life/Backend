package projeto.fisio4life.fenix.Dto.exercicioPassado;

import jakarta.validation.constraints.*;
import java.time.LocalTime;

public class ExercicioPassadoRequestDto {

    @NotNull
    private Integer idExercicio;

    @NotNull
    private Integer idEvolucao;

    @NotBlank
    @Size(max = 45)
    private String segmento;

    @NotNull
    @Min(0)
    private Integer quantidadeSeries;

    @NotNull
    @Min(0)
    private Integer quantidadeRepeticoes;

    @NotNull
    @Min(0)
    private Integer carga;

    @NotNull
    private LocalTime tempo;

    @NotBlank
    @Size(max = 45)
    private String local;

    public ExercicioPassadoRequestDto() {}

    public Integer getIdExercicio() { return idExercicio; }
    public void setIdExercicio(Integer valor) { this.idExercicio = valor; }

    public Integer getIdEvolucao() { return idEvolucao; }
    public void setIdEvolucao(Integer valor) { this.idEvolucao = valor; }

    public String getSegmento() { return segmento; }
    public void setSegmento(String valor) { this.segmento = valor; }

    public Integer getQuantidadeSeries() { return quantidadeSeries; }
    public void setQuantidadeSeries(Integer valor) { this.quantidadeSeries = valor; }

    public Integer getQuantidadeRepeticoes() { return quantidadeRepeticoes; }
    public void setQuantidadeRepeticoes(Integer valor) { this.quantidadeRepeticoes = valor; }

    public Integer getCarga() { return carga; }
    public void setCarga(Integer valor) { this.carga = valor; }

    public LocalTime getTempo() { return tempo; }
    public void setTempo(LocalTime valor) { this.tempo = valor; }

    public String getLocal() { return local; }
    public void setLocal(String valor) { this.local = valor; }

}
