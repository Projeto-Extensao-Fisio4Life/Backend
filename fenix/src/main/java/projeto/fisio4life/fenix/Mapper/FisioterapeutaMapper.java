package projeto.fisio4life.fenix.Mapper;


import projeto.fisio4life.fenix.Dto.fisioterapeutaDto.FisioterapeutaRequestDto;
import projeto.fisio4life.fenix.Dto.fisioterapeutaDto.FisioterapeutaResponseDto;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public final class FisioterapeutaMapper {

    private FisioterapeutaMapper() {
    }

    public static Fisioterapeuta toEntity(FisioterapeutaRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Fisioterapeuta entity = new Fisioterapeuta();

        entity.setCrefito(dto.getCrefito());
        entity.setEspecialidade(dto.getEspecialidade());
        entity.setCnpj(dto.getCnpj());
        if (dto.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setId_usuario(dto.getIdUsuario());
            entity.setUsuario(usuario);
        }

        return entity;
    }

    public static FisioterapeutaResponseDto toResponseDto(Fisioterapeuta entity) {
        if (entity == null) {
            return null;
        }

        FisioterapeutaResponseDto dto = new FisioterapeutaResponseDto();

        dto.setIdFisioterapeuta(entity.getId_fisioterapeuta());
        dto.setCrefito(entity.getCrefito());
        dto.setEspecialidade(entity.getEspecialidade());
        dto.setCnpj(entity.getCnpj());
        if (entity.getUsuario() != null) {
            dto.setIdUsuario(entity.getUsuario().getId_usuario());
        }

        return dto;
    }

    public static List<FisioterapeutaResponseDto> toResponseDto(List<Fisioterapeuta> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(FisioterapeutaMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}