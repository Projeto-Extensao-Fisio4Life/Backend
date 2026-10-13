package projeto.fisio4life.fenix.Mapper;


import projeto.fisio4life.fenix.Dto.sessaoDto.SessaoRequestDto;
import projeto.fisio4life.fenix.Dto.sessaoDto.SessaoResponseDto;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Entity.Sessao;

import java.util.List;
import java.util.stream.Collectors;

public final class SessaoMapper {

    private SessaoMapper() {
    }

    public static Sessao toEntity(SessaoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Sessao entity = new Sessao();

        entity.setData_sessao(dto.getDataSessao());
        entity.setDescricao(dto.getDescricao());
        entity.setObservacao(dto.getObservacao());
        if (dto.getIdAgendamento() != null) {
            Agendamento sessao = new Agendamento();
            sessao.setId_agendamento(dto.getIdAgendamento());
            entity.setAgendamento(sessao);
        }

        return entity;
    }

    public static SessaoResponseDto toResponseDto(Sessao entity) {
        if (entity == null) {
            return null;
        }

        SessaoResponseDto dto = new SessaoResponseDto();

        dto.setIdSessao(entity.getId_sessao());
        dto.setDataSessao(entity.getData_sessao());
        dto.setDescricao(entity.getDescricao());
        dto.setObservacao(entity.getObservacao());
        if (entity.getAgendamento() != null) {
            dto.setIdAgendamento(entity.getAgendamento().getId_agendamento());
        }

        return dto;
    }

    public static List<SessaoResponseDto> toResponseDto(List<Sessao> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(SessaoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}