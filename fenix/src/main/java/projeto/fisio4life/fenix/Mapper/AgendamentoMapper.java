package projeto.fisio4life.fenix.Mapper;


import projeto.fisio4life.fenix.Dto.agendamentoDto.AgendamentoRequestDto;
import projeto.fisio4life.fenix.Dto.agendamentoDto.AgendamentoResponseDto;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Paciente;

import java.util.List;
import java.util.stream.Collectors;

public final class AgendamentoMapper {

    private AgendamentoMapper() {
    }

    public static Agendamento toEntity(AgendamentoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Agendamento entity = new Agendamento();

        entity.setData_agendamento(dto.getDataAgendamento());
        entity.setHora_inicio(dto.getHoraInicio());
        entity.setHora_fim(dto.getHoraFim());
        entity.setStatus(dto.getStatusAgendamento());
        entity.setObservacao(dto.getObservacao());
        if (dto.getIdClinica() != null) {
            Clinica clinica = new Clinica();
            clinica.setId_clinica(dto.getIdClinica());
            entity.setClinica(clinica);
        }
        if (dto.getIdFisioterapeuta() != null) {
            Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
            fisioterapeuta.setId_fisioterapeuta(dto.getIdFisioterapeuta());
            entity.setFisioterapeuta(fisioterapeuta);
        }
        if (dto.getIdPaciente() != null) {
            Paciente paciente = new Paciente();
            paciente.setId_paciente(dto.getIdPaciente());
            entity.setPaciente(paciente);
        }

        return entity;
    }

    public static AgendamentoResponseDto toResponseDto(Agendamento entity) {
        if (entity == null) {
            return null;
        }

        AgendamentoResponseDto dto = new AgendamentoResponseDto();

        dto.setIdAgendamento(entity.getId_agendamento());
        dto.setDataAgendamento(entity.getData_agendamento());
        dto.setHoraInicio(entity.getHora_inicio());
        dto.setHoraFim(entity.getHora_fim());
        dto.setStatusAgendamento(entity.getStatus());
        dto.setObservacao(entity.getObservacao());
        if (entity.getClinica() != null) {
            dto.setIdClinica(entity.getClinica().getId_clinica());
        }
        if (entity.getFisioterapeuta() != null) {
            dto.setIdFisioterapeuta(entity.getFisioterapeuta().getId_fisioterapeuta());
        }
        if (entity.getPaciente() != null) {
            dto.setIdPaciente(entity.getPaciente().getId_paciente());
        }

        return dto;
    }

    public static List<AgendamentoResponseDto> toResponseDto(List<Agendamento> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(AgendamentoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}