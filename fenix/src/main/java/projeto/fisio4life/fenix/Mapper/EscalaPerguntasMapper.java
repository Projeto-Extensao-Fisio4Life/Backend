package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.escalaPerguntasDto.EscalaPerguntasRequestDto;
import projeto.fisio4life.fenix.Dto.escalaPerguntasDto.EscalaPerguntasResponseDto;
import projeto.fisio4life.fenix.Entity.Escala;
import projeto.fisio4life.fenix.Entity.EscalaPerguntas;

public final class EscalaPerguntasMapper {

    private EscalaPerguntasMapper() {}

    public static EscalaPerguntas toEntity(EscalaPerguntasRequestDto dto) {
        if (dto == null) return null;

        EscalaPerguntas entity = new EscalaPerguntas();

        if (dto.getIdEscala() != null) {
            Escala ref = new Escala();
            ref.setId_escala(dto.getIdEscala());
            entity.setEscala(ref);
        }
        entity.setTitulo_pergunta(dto.getTituloPergunta());
        entity.setTipo_dado(dto.getTipoDado());

        return entity;
    }

    public static EscalaPerguntasResponseDto toResponseDto(EscalaPerguntas entity) {
        if (entity == null) return null;

        EscalaPerguntasResponseDto dto = new EscalaPerguntasResponseDto();
        dto.setIdEscalaPerguntas(entity.getId_escala_perguntas());
        if (entity.getEscala() != null) dto.setIdEscala(entity.getEscala().getId_escala());
        dto.setTituloPergunta(entity.getTitulo_pergunta());
        dto.setTipoDado(entity.getTipo_dado());

        return dto;
    }

    public static List<EscalaPerguntasResponseDto> toResponseDto(List<EscalaPerguntas> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(EscalaPerguntasMapper::toResponseDto).collect(Collectors.toList());
    }
}
