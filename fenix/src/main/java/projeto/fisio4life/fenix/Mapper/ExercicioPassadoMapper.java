package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.exercicioPassado.ExercicioPassadoRequestDto;
import projeto.fisio4life.fenix.Dto.exercicioPassado.ExercicioPassadoResponseDto;
import projeto.fisio4life.fenix.Entity.Evolucao;
import projeto.fisio4life.fenix.Entity.Exercicio;
import projeto.fisio4life.fenix.Entity.ExercicioPassado;

public final class ExercicioPassadoMapper {

    private ExercicioPassadoMapper() {}

    public static ExercicioPassado toEntity(ExercicioPassadoRequestDto dto) {
        if (dto == null) return null;

        ExercicioPassado entity = new ExercicioPassado();

        if (dto.getIdExercicio() != null) {
            Exercicio ref = new Exercicio();
            ref.setId_exercicio(dto.getIdExercicio());
            entity.setExercicio(ref);
        }
        if (dto.getIdEvolucao() != null) {
            Evolucao ref = new Evolucao();
            ref.setId_evolucao(dto.getIdEvolucao());
            entity.setEvolucao(ref);
        }
        entity.setSegmento(dto.getSegmento());
        entity.setQuantidade_series(dto.getQuantidadeSeries());
        entity.setQuantidade_repeticoes(dto.getQuantidadeRepeticoes());
        entity.setCarga(dto.getCarga());
        entity.setTempo(dto.getTempo());
        entity.setLocal(dto.getLocal());

        return entity;
    }

    public static ExercicioPassadoResponseDto toResponseDto(ExercicioPassado entity) {
        if (entity == null) return null;

        ExercicioPassadoResponseDto dto = new ExercicioPassadoResponseDto();
        dto.setIdExercicioPassado(entity.getId_exercicio_passado());
        if (entity.getExercicio() != null) dto.setIdExercicio(entity.getExercicio().getId_exercicio());
        if (entity.getEvolucao() != null) dto.setIdEvolucao(entity.getEvolucao().getId_evolucao());
        dto.setSegmento(entity.getSegmento());
        dto.setQuantidadeSeries(entity.getQuantidade_series());
        dto.setQuantidadeRepeticoes(entity.getQuantidade_repeticoes());
        dto.setCarga(entity.getCarga());
        dto.setTempo(entity.getTempo());
        dto.setLocal(entity.getLocal());

        return dto;
    }

    public static List<ExercicioPassadoResponseDto> toResponseDto(List<ExercicioPassado> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(ExercicioPassadoMapper::toResponseDto).collect(Collectors.toList());
    }
}
