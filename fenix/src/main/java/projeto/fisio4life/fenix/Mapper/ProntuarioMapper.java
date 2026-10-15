package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.prontuarioDto.ProntuarioRequestDto;
import projeto.fisio4life.fenix.Dto.prontuarioDto.ProntuarioResponseDto;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Prontuario;

public final class ProntuarioMapper {

    private ProntuarioMapper() {}

    public static Prontuario toEntity(ProntuarioRequestDto dto) {
        if (dto == null) return null;

        Prontuario entity = new Prontuario();

        if (dto.getIdPaciente() != null) {
            Paciente ref = new Paciente();
            ref.setId_paciente(dto.getIdPaciente());
            entity.setPaciente(ref);
        }
        entity.setStatus_prontuario(dto.getStatusProntuario() == null ? null : dto.getStatusProntuario().byteValue());
        entity.setObservacao_inicial(dto.getObservacaoInicial());

        return entity;
    }

    public static ProntuarioResponseDto toResponseDto(Prontuario entity) {
        if (entity == null) return null;

        ProntuarioResponseDto dto = new ProntuarioResponseDto();
        dto.setIdProntuario(entity.getId_prontuario());
        if (entity.getPaciente() != null) dto.setIdPaciente(entity.getPaciente().getId_paciente());
        dto.setDataAbertura(entity.getData_abertura());
        dto.setStatusProntuario(entity.getStatus_prontuario() == null ? null : entity.getStatus_prontuario().intValue());
        dto.setObservacaoInicial(entity.getObservacao_inicial());

        return dto;
    }

    public static List<ProntuarioResponseDto> toResponseDto(List<Prontuario> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(ProntuarioMapper::toResponseDto).collect(Collectors.toList());
    }
}
