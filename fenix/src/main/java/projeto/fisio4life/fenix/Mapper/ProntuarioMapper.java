package projeto.fisio4life.fenix.Mapper;

import projeto.fisio4life.fenix.Dto.prontuarioDto.ProntuarioRequestDto;
import projeto.fisio4life.fenix.Dto.prontuarioDto.ProntuarioResponseDto;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Prontuario;

import java.util.List;
import java.util.stream.Collectors;

public final class ProntuarioMapper {

    private ProntuarioMapper() {
    }

    public static Prontuario toEntity(ProntuarioRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Prontuario entity = new Prontuario();

        entity.setData_abertura(dto.getDataAbertura());
        entity.setStatus(dto.getStatusProntuario());
        entity.setObservacao_inicial(dto.getObservacaoInicial());
        if (dto.getIdPaciente() != null) {
            Paciente paciente = new Paciente();
            paciente.setId_paciente(dto.getIdPaciente());
            entity.setPaciente(paciente);
        }

        return entity;
    }

    public static ProntuarioResponseDto toResponseDto(Prontuario entity) {
        if (entity == null) {
            return null;
        }

        ProntuarioResponseDto dto = new ProntuarioResponseDto();

        dto.setIdProntuario(entity.getId_prontuario());
        dto.setDataAbertura(entity.getData_abertura());
        dto.setStatusProntuario(entity.getStatus());
        dto.setObservacaoInicial(entity.getObservacao_inicial());
        if (entity.getPaciente() != null) {
            dto.setIdPaciente(entity.getPaciente().getId_paciente());
        }

        return dto;
    }

    public static List<ProntuarioResponseDto> toResponseDto(List<Prontuario> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(ProntuarioMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}