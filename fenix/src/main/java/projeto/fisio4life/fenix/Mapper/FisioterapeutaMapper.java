package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.fisioterapeutaDto.FisioterapeutaRequestDto;
import projeto.fisio4life.fenix.Dto.fisioterapeutaDto.FisioterapeutaResponseDto;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Usuario;

public final class FisioterapeutaMapper {

    private FisioterapeutaMapper() {}

    public static Fisioterapeuta toEntity(FisioterapeutaRequestDto dto) {
        if (dto == null) return null;

        Fisioterapeuta entity = new Fisioterapeuta();

        if (dto.getIdUsuario() != null) {
            Usuario ref = new Usuario();
            ref.setId_usuario(dto.getIdUsuario());
            entity.setUsuario(ref);
        }
        entity.setCrefito(dto.getCrefito());
        entity.setEspecialidade(dto.getEspecialidade());
        entity.setCnpj(dto.getCnpj());

        return entity;
    }

    public static FisioterapeutaResponseDto toResponseDto(Fisioterapeuta entity) {
        if (entity == null) return null;

        FisioterapeutaResponseDto dto = new FisioterapeutaResponseDto();
        dto.setIdFisioterapeuta(entity.getId_fisioterapeuta());
        if (entity.getUsuario() != null) dto.setIdUsuario(entity.getUsuario().getId_usuario());
        dto.setCrefito(entity.getCrefito());
        dto.setEspecialidade(entity.getEspecialidade());
        dto.setCnpj(entity.getCnpj());

        return dto;
    }

    public static List<FisioterapeutaResponseDto> toResponseDto(List<Fisioterapeuta> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(FisioterapeutaMapper::toResponseDto).collect(Collectors.toList());
    }
}
