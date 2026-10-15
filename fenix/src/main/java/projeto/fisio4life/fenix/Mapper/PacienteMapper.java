package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.pacienteDto.PacienteRequestDto;
import projeto.fisio4life.fenix.Dto.pacienteDto.PacienteResponseDto;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Usuario;

public final class PacienteMapper {

    private PacienteMapper() {}

    public static Paciente toEntity(PacienteRequestDto dto) {
        if (dto == null) return null;

        Paciente entity = new Paciente();

        if (dto.getIdUsuario() != null) {
            Usuario ref = new Usuario();
            ref.setId_usuario(dto.getIdUsuario());
            entity.setUsuario(ref);
        }
        entity.setCpf(dto.getCpf());
        entity.setPermite_atendimento_grupo(dto.getPermiteAtendimentoGrupo());

        return entity;
    }

    public static PacienteResponseDto toResponseDto(Paciente entity) {
        if (entity == null) return null;

        PacienteResponseDto dto = new PacienteResponseDto();
        dto.setIdPaciente(entity.getId_paciente());
        if (entity.getUsuario() != null) dto.setIdUsuario(entity.getUsuario().getId_usuario());
        dto.setCpf(entity.getCpf());
        dto.setPermiteAtendimentoGrupo(entity.getPermite_atendimento_grupo());

        return dto;
    }

    public static List<PacienteResponseDto> toResponseDto(List<Paciente> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(PacienteMapper::toResponseDto).collect(Collectors.toList());
    }
}
