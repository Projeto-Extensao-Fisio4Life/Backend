package projeto.fisio4life.fenix.Mapper;


import projeto.fisio4life.fenix.Dto.contatoDto.ContatoRequestDto;
import projeto.fisio4life.fenix.Dto.contatoDto.ContatoResponseDto;
import projeto.fisio4life.fenix.Entity.Contato;

import java.util.List;
import java.util.stream.Collectors;

public final class ContatoMapper {

    private ContatoMapper() {
    }

    public static Contato toEntity(ContatoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Contato entity = new Contato();

        entity.setTelefone(dto.getTelefone());
        entity.setCelular(dto.getCelular());
        entity.setEmail(dto.getEmail());

        return entity;
    }

    public static ContatoResponseDto toResponseDto(Contato entity) {
        if (entity == null) {
            return null;
        }

        ContatoResponseDto dto = new ContatoResponseDto();

        dto.setIdContato(entity.getId_contato());
        dto.setTelefone(entity.getTelefone());
        dto.setCelular(entity.getCelular());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    public static List<ContatoResponseDto> toResponseDto(List<Contato> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(ContatoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}