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

        Agendamento agendamento = new Agendamento();
        agendamento.setId_agendamento(dto.getIdAgendamento());

        Servico servico = new Servico();
        servico.setId_servico(dto.getIdServico());

        entity.setAgendamento(agendamento);
        entity.setServico(servico);
        entity.setData_sessao(dto.getDataSessao());
        entity.setDescricao(dto.getDescricao());
        entity.setObservacao(dto.getObservacao());
        entity.setStatus_sessao(dto.getStatus_sessao());
        entity.setGera_cobranca(dto.getGera_cobranca());

        return entity;
    }

    public static SessaoResponseDto toResponseDto(Sessao entity) {
        if (entity == null) return null;

        SessaoResponseDto dto = new SessaoResponseDto();
        dto.setIdSessao(entity.getId_sessao());
        dto.setAgendamento(AgendamentoMapper.toResponseDto(entity.getAgendamento()));
        dto.setServico(ServicoMapper.toResponseDto(entity.getServico()));
        dto.setDataSessao(entity.getData_sessao());
        dto.setDescricao(entity.getDescricao());
        dto.setObservacao(entity.getObservacao());
        dto.setStatus_sessao(entity.getStatus_sessao());
        dto.setGera_cobranca(entity.getGera_cobranca());


        return dto;
    }

    public static List<SessaoResponseDto> toResponseDto(List<Sessao> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(SessaoMapper::toResponseDto).collect(Collectors.toList());
    }
}
