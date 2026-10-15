package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.escalaDto.EscalaRequestDto;
import projeto.fisio4life.fenix.Dto.escalaDto.EscalaResponseDto;
import projeto.fisio4life.fenix.Entity.Escala;

public final class EscalaMapper {

    private EscalaMapper() {}

    public static Escala toEntity(EscalaRequestDto dto) {
        if (dto == null) return null;

        Escala entity = new Escala();

        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());

        return entity;
    }

    public static EscalaResponseDto toResponseDto(Escala entity) {
        if (entity == null) return null;

        EscalaResponseDto dto = new EscalaResponseDto();
        dto.setIdEscala(entity.getId_escala());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());

        return dto;
    }

    public static List<EscalaResponseDto> toResponseDto(List<Escala> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(EscalaMapper::toResponseDto).collect(Collectors.toList());
    }
}
