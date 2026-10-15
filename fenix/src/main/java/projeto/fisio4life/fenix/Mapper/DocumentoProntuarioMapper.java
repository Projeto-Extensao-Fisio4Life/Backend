package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.documentoProntuarioDto.DocumentoProntuarioRequestDto;
import projeto.fisio4life.fenix.Dto.documentoProntuarioDto.DocumentoProntuarioResponseDto;
import projeto.fisio4life.fenix.Entity.DocumentoProntuario;
import projeto.fisio4life.fenix.Entity.Prontuario;

public final class DocumentoProntuarioMapper {

    private DocumentoProntuarioMapper() {}

    public static DocumentoProntuario toEntity(DocumentoProntuarioRequestDto dto) {
        if (dto == null) return null;

        DocumentoProntuario entity = new DocumentoProntuario();

        if (dto.getIdProntuario() != null) {
            Prontuario ref = new Prontuario();
            ref.setId_prontuario(dto.getIdProntuario());
            entity.setProntuario(ref);
        }
        entity.setNome_arquivo(dto.getNomeArquivo());
        entity.setTipo_documento(dto.getTipoDocumento());
        entity.setS3_key(dto.getS3Key());
        entity.setUrl_documento(dto.getUrlDocumento());
        entity.setData_upload(dto.getDataUpload());

        return entity;
    }

    public static DocumentoProntuarioResponseDto toResponseDto(DocumentoProntuario entity) {
        if (entity == null) return null;

        DocumentoProntuarioResponseDto dto = new DocumentoProntuarioResponseDto();
        dto.setIdDocumento(entity.getId_documento());
        if (entity.getProntuario() != null) dto.setIdProntuario(entity.getProntuario().getId_prontuario());
        dto.setNomeArquivo(entity.getNome_arquivo());
        dto.setTipoDocumento(entity.getTipo_documento());
        dto.setS3Key(entity.getS3_key());
        dto.setUrlDocumento(entity.getUrl_documento());
        dto.setDataUpload(entity.getData_upload());

        return dto;
    }

    public static List<DocumentoProntuarioResponseDto> toResponseDto(List<DocumentoProntuario> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(DocumentoProntuarioMapper::toResponseDto).collect(Collectors.toList());
    }
}
