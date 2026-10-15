package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.escalaRespostasDto.EscalaRespostasRequestDto;
import projeto.fisio4life.fenix.Dto.escalaRespostasDto.EscalaRespostasResponseDto;
import projeto.fisio4life.fenix.Entity.EscalaPerguntas;
import projeto.fisio4life.fenix.Entity.EscalaProntuario;
import projeto.fisio4life.fenix.Entity.EscalaRespostas;

public final class EscalaRespostasMapper {

    private EscalaRespostasMapper() {}

    public static EscalaRespostas toEntity(EscalaRespostasRequestDto dto) {
        if (dto == null) return null;

        EscalaRespostas entity = new EscalaRespostas();

        if (dto.getIdEscalaProntuario() != null) {
            EscalaProntuario ref = new EscalaProntuario();
            ref.setId_escala_prontuario(dto.getIdEscalaProntuario());
            entity.setEscala_prontuario(ref);
        }
        if (dto.getIdEscalaPerguntas() != null) {
            EscalaPerguntas ref = new EscalaPerguntas();
            ref.setId_escala_perguntas(dto.getIdEscalaPerguntas());
            entity.setEscala_perguntas(ref);
        }
        entity.setValor_resposta(dto.getValorResposta());

        return entity;
    }

    public static EscalaRespostasResponseDto toResponseDto(EscalaRespostas entity) {
        if (entity == null) return null;

        EscalaRespostasResponseDto dto = new EscalaRespostasResponseDto();
        dto.setIdEscalaRespostas(entity.getId_escala_respostas());
        if (entity.getEscala_prontuario() != null) dto.setIdEscalaProntuario(entity.getEscala_prontuario().getId_escala_prontuario());
        if (entity.getEscala_perguntas() != null) dto.setIdEscalaPerguntas(entity.getEscala_perguntas().getId_escala_perguntas());
        dto.setValorResposta(entity.getValor_resposta());

        return dto;
    }

    public static List<EscalaRespostasResponseDto> toResponseDto(List<EscalaRespostas> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(EscalaRespostasMapper::toResponseDto).collect(Collectors.toList());
    }
}
