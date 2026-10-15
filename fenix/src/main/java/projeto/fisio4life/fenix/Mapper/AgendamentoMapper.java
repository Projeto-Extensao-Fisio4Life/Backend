package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.agendamentoDto.AgendamentoRequestDto;
import projeto.fisio4life.fenix.Dto.agendamentoDto.AgendamentoResponseDto;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Paciente;

public final class AgendamentoMapper {

    private AgendamentoMapper() {}

    public static Agendamento toEntity(AgendamentoRequestDto dto) {
        if (dto == null) return null;

        Agendamento entity = new Agendamento();

        if (dto.getIdClinica() != null) {
            Clinica ref = new Clinica();
            ref.setId_clinica(dto.getIdClinica());
            entity.setClinica(ref);
        }
        if (dto.getIdFisioterapeuta() != null) {
            Fisioterapeuta ref = new Fisioterapeuta();
            ref.setId_fisioterapeuta(dto.getIdFisioterapeuta());
            entity.setFisioterapeuta(ref);
        }
        if (dto.getIdPaciente() != null) {
            Paciente ref = new Paciente();
            ref.setId_paciente(dto.getIdPaciente());
            entity.setPaciente(ref);
        }
        entity.setData_agendamento(dto.getDataAgendamento());
        entity.setHora_inicio(dto.getHoraInicio());
        entity.setHora_fim(dto.getHoraFim());
        entity.setStatus_agendamento(dto.getStatusAgendamento() == null ? null : dto.getStatusAgendamento().byteValue());
        entity.setObservacao(dto.getObservacao());

        return entity;
    }

    public static AgendamentoResponseDto toResponseDto(Agendamento entity) {
        if (entity == null) return null;

        AgendamentoResponseDto dto = new AgendamentoResponseDto();
        dto.setIdAgendamento(entity.getId_agendamento());
        if (entity.getClinica() != null) dto.setIdClinica(entity.getClinica().getId_clinica());
        if (entity.getFisioterapeuta() != null) dto.setIdFisioterapeuta(entity.getFisioterapeuta().getId_fisioterapeuta());
        if (entity.getPaciente() != null) dto.setIdPaciente(entity.getPaciente().getId_paciente());
        dto.setDataAgendamento(entity.getData_agendamento());
        dto.setHoraInicio(entity.getHora_inicio());
        dto.setHoraFim(entity.getHora_fim());
        dto.setStatusAgendamento(entity.getStatus_agendamento() == null ? null : entity.getStatus_agendamento().intValue());
        dto.setObservacao(entity.getObservacao());

        return dto;
    }

    public static List<AgendamentoResponseDto> toResponseDto(List<Agendamento> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(AgendamentoMapper::toResponseDto).collect(Collectors.toList());
    }
}
