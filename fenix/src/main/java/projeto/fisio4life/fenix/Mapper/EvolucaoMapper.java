package projeto.fisio4life.fenix.Mapper;
import projeto.fisio4life.fenix.Dto.evolucaoDto.EvolucaoRequestDto;
import projeto.fisio4life.fenix.Dto.evolucaoDto.EvolucaoResponseDto;
import projeto.fisio4life.fenix.Entity.Evolucao;
import projeto.fisio4life.fenix.Entity.Prontuario;
import projeto.fisio4life.fenix.Entity.Sessao;

import java.util.List;
import java.util.stream.Collectors;

public final class EvolucaoMapper {

    private EvolucaoMapper() {
    }

    public static Evolucao toEntity(EvolucaoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Evolucao entity = new Evolucao();

        entity.setData_evolucao(dto.getDataEvolucao());
        entity.setConduta(dto.getConduta());
        entity.setAvaliacao(dto.getAvaliacao());
        entity.setObservacao(dto.getObservacao());
        if (dto.getIdSessao() != null) {
            Sessao sessao = new Sessao();
            sessao.setId_sessao(dto.getIdSessao());
            entity.setSessao(sessao);
        }
        if (dto.getIdProntuario() != null) {
            Prontuario prontuario = new Prontuario();
            prontuario.setId_prontuario(dto.getIdProntuario());
            entity.setProntuario(prontuario);
        }

        return entity;
    }

    public static EvolucaoResponseDto toResponseDto(Evolucao entity) {
        if (entity == null) {
            return null;
        }

        EvolucaoResponseDto dto = new EvolucaoResponseDto();

        dto.setIdEvolucao(entity.getId_evolucao());
        dto.setDataEvolucao(entity.getData_evolucao());
        dto.setConduta(entity.getConduta());
        dto.setAvaliacao(entity.getAvaliacao());
        dto.setObservacao(entity.getObservacao());
        if (entity.getSessao() != null) {
            dto.setIdSessao(entity.getSessao().getId_sessao());
        }
        if (entity.getProntuario() != null) {
            dto.setIdProntuario(entity.getProntuario().getId_prontuario());
        }

        return dto;
    }

    public static List<EvolucaoResponseDto> toResponseDto(List<Evolucao> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(EvolucaoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}