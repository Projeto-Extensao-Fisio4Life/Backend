package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.servicoDto.ServicoRequestDto;
import projeto.fisio4life.fenix.Dto.servicoDto.ServicoResponseDto;
import projeto.fisio4life.fenix.Entity.Servico;

public final class ServicoMapper {

    private ServicoMapper() {}

    public static Servico toEntity(ServicoRequestDto dto) {
        if (dto == null) return null;

        Servico entity = new Servico();

        entity.setNome(dto.getNome());
        entity.setQuantidade_sessoes(dto.getQuantidadeSessoes());
        entity.setTipo(dto.getTipo());
        entity.setStatus_servico(dto.getStatusServico() == null ? null : dto.getStatusServico().byteValue());

        return entity;
    }

    public static ServicoResponseDto toResponseDto(Servico entity) {
        if (entity == null) return null;

        ServicoResponseDto dto = new ServicoResponseDto();
        dto.setIdServico(entity.getId_servico());
        dto.setNome(entity.getNome());
        dto.setQuantidade_sessoes(entity.getQuantidade_sessoes());
        dto.setTipo(entity.getTipo());
        dto.setStatusServico(entity.getStatus_servico() == null ? null : entity.getStatus_servico().intValue());

        return dto;
    }

    public static List<ServicoResponseDto> toResponseDto(List<Servico> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(ServicoMapper::toResponseDto).collect(Collectors.toList());
    }
}
