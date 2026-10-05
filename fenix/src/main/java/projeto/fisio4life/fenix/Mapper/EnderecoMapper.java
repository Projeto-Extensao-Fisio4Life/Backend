package projeto.fisio4life.fenix.Mapper;

import projeto.fisio4life.fenix.Dto.enderecoDto.EnderecoRequestDto;
import projeto.fisio4life.fenix.Dto.enderecoDto.EnderecoResponseDto;
import projeto.fisio4life.fenix.Entity.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public final class EnderecoMapper {

    private EnderecoMapper() {
    }

    public static Endereco toEntity(EnderecoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Endereco entity = new Endereco();

        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());

        return entity;
    }

    public static EnderecoResponseDto toResponseDto(Endereco entity) {
        if (entity == null) {
            return null;
        }

        EnderecoResponseDto dto = new EnderecoResponseDto();

        dto.setIdEndereco(entity.getId_endereco());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setBairro(entity.getBairro());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        dto.setCep(entity.getCep());

        return dto;
    }

    public static List<EnderecoResponseDto> toResponseDto(List<Endereco> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(EnderecoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}