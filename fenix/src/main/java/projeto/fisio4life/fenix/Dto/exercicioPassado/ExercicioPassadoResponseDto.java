package projeto.fisio4life.fenix.Dto.exercicioPassado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ExercicioPassadoResponseDto {

    private Integer idExercicioPassado;

    private Integer idExercicio;

    private Integer idEvolucao;

    private String segmento;

    private Integer quantidadeSeries;

    private Integer quantidadeRepeticoes;

    private Integer carga;

    private LocalTime tempo;

    private String local;

    public ExercicioPassadoResponseDto() {}

    public Integer getIdExercicioPassado() { return idExercicioPassado; }
    public void setIdExercicioPassado(Integer valor) { this.idExercicioPassado = valor; }

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
