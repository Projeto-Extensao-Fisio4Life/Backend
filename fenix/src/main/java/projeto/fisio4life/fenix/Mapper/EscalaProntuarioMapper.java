package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.escalaProntuarioDto.EscalaProntuarioRequestDto;
import projeto.fisio4life.fenix.Dto.escalaProntuarioDto.EscalaProntuarioResponseDto;
import projeto.fisio4life.fenix.Entity.Escala;
import projeto.fisio4life.fenix.Entity.EscalaProntuario;
import projeto.fisio4life.fenix.Entity.Prontuario;

public final class EscalaProntuarioMapper {

    private EscalaProntuarioMapper() {}

    public static EscalaProntuario toEntity(EscalaProntuarioRequestDto dto) {
        if (dto == null) return null;

        EscalaProntuario entity = new EscalaProntuario();

        if (dto.getIdProntuario() != null) {
            Prontuario ref = new Prontuario();
            ref.setId_prontuario(dto.getIdProntuario());
            entity.setProntuario(ref);
        }
        if (dto.getIdEscala() != null) {
            Escala ref = new Escala();
            ref.setId_escala(dto.getIdEscala());
            entity.setEscala(ref);
        }
        entity.setData_resposta(dto.getDataResposta());

        return entity;
    }

    public static EscalaProntuarioResponseDto toResponseDto(EscalaProntuario entity) {
        if (entity == null) return null;

        EscalaProntuarioResponseDto dto = new EscalaProntuarioResponseDto();
        dto.setIdEscalaProntuario(entity.getId_escala_prontuario());
        if (entity.getProntuario() != null) dto.setIdProntuario(entity.getProntuario().getId_prontuario());
        if (entity.getEscala() != null) dto.setIdEscala(entity.getEscala().getId_escala());
        dto.setDataResposta(entity.getData_resposta());

        return dto;
    }

    public static List<EscalaProntuarioResponseDto> toResponseDto(List<EscalaProntuario> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(EscalaProntuarioMapper::toResponseDto).collect(Collectors.toList());
    }
}
