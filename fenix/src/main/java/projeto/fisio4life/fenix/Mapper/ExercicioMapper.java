package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.exercicioDto.ExercicioRequestDto;
import projeto.fisio4life.fenix.Dto.exercicioDto.ExercicioResponseDto;
import projeto.fisio4life.fenix.Entity.Exercicio;

public final class ExercicioMapper {

    private ExercicioMapper() {}

    public static Exercicio toEntity(ExercicioRequestDto dto) {
        if (dto == null) return null;

        Exercicio entity = new Exercicio();

        entity.setCategoria(dto.getCategoria());
        entity.setNome(dto.getNome());

        return entity;
    }

    public static ExercicioResponseDto toResponseDto(Exercicio entity) {
        if (entity == null) return null;

        ExercicioResponseDto dto = new ExercicioResponseDto();
        dto.setIdExercicio(entity.getId_exercicio());
        dto.setCategoria(entity.getCategoria());
        dto.setNome(entity.getNome());

        return dto;
    }

    public static List<ExercicioResponseDto> toResponseDto(List<Exercicio> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(ExercicioMapper::toResponseDto).collect(Collectors.toList());
    }
}
