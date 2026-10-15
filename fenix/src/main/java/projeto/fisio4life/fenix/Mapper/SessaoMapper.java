package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.sessaoDto.SessaoRequestDto;
import projeto.fisio4life.fenix.Dto.sessaoDto.SessaoResponseDto;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Entity.Servico;
import projeto.fisio4life.fenix.Entity.Sessao;

public final class SessaoMapper {

    private SessaoMapper() {}

    public static Sessao toEntity(SessaoRequestDto dto) {
        if (dto == null) return null;

        Sessao entity = new Sessao();

        if (dto.getIdAgendamento() != null) {
            Agendamento ref = new Agendamento();
            ref.setId_agendamento(dto.getIdAgendamento());
            entity.setAgendamento(ref);
        }
        if (dto.getIdServico() != null) {
            Servico ref = new Servico();
            ref.setId_servico(dto.getIdServico());
            entity.setServico(ref);
        }
        entity.setData_sessao(dto.getDataSessao());
        entity.setDescricao(dto.getDescricao());
        entity.setObservacao(dto.getObservacao());

        return entity;
    }

    public static SessaoResponseDto toResponseDto(Sessao entity) {
        if (entity == null) return null;

        SessaoResponseDto dto = new SessaoResponseDto();
        dto.setIdSessao(entity.getId_sessao());
        if (entity.getAgendamento() != null) dto.setIdAgendamento(entity.getAgendamento().getId_agendamento());
        if (entity.getServico() != null) dto.setIdServico(entity.getServico().getId_servico());
        dto.setDataSessao(entity.getData_sessao());
        dto.setDescricao(entity.getDescricao());
        dto.setObservacao(entity.getObservacao());

        return dto;
    }

    public static List<SessaoResponseDto> toResponseDto(List<Sessao> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(SessaoMapper::toResponseDto).collect(Collectors.toList());
    }
}
