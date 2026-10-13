package projeto.fisio4life.fenix.Mapper;

import projeto.fisio4life.fenix.Dto.servicoDto.ServicoRequestDto;
import projeto.fisio4life.fenix.Dto.servicoDto.ServicoResponseDto;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Entity.Servico;

import java.util.List;
import java.util.stream.Collectors;

public final class ServicoMapper {

    private ServicoMapper() {
    }

    public static Servico toEntity(ServicoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Servico entity = new Servico();

        entity.setNome(dto.getNome());
        entity.setQuantidade_sessoes(dto.getQuantidadeSessoes());
        entity.setValor(dto.getValor());
        entity.setTipo(dto.getTipo());
        if (dto.getIdClinica() != null) {
            Clinica clinica = new Clinica();
            clinica.setId_clinica(dto.getIdClinica());
            entity.setClinica(clinica);
        }

        return entity;
    }

    public static ServicoResponseDto toResponseDto(Servico entity) {
        if (entity == null) {
            return null;
        }

        ServicoResponseDto dto = new ServicoResponseDto();

        dto.setIdServico(entity.getId_servico());
        dto.setNome(entity.getNome());
        dto.setQuantidadeSessoes(entity.getQuantidade_sessoes());
        dto.setValor(entity.getValor());
        dto.setTipo(entity.getTipo());
        if (entity.getClinica() != null) {
            dto.setIdClinica(entity.getClinica().getId_clinica());
        }

        return dto;
    }

    public static List<ServicoResponseDto> toResponseDto(List<Servico> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(ServicoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}