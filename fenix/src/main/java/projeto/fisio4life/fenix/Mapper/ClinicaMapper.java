package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaRequestDto;
import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaResponseDto;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Entity.Contato;
import projeto.fisio4life.fenix.Entity.Endereco;

public final class ClinicaMapper {

    private ClinicaMapper() {}

    public static Clinica toEntity(ClinicaRequestDto dto) {
        if (dto == null) return null;

        Clinica entity = new Clinica();

        if (dto.getIdContato() != null) {
            Contato ref = new Contato();
            ref.setId_contato(dto.getIdContato());
            entity.setContato(ref);
        }
        if (dto.getIdEndereco() != null) {
            Endereco ref = new Endereco();
            ref.setId_endereco(dto.getIdEndereco());
            entity.setEndereco(ref);
        }
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());

        return entity;
    }

    public static ClinicaResponseDto toResponseDto(Clinica entity) {
        if (entity == null) return null;

        ClinicaResponseDto dto = new ClinicaResponseDto();
        dto.setIdClinica(entity.getId_clinica());
        if (entity.getContato() != null) dto.setIdContato(entity.getContato().getId_contato());
        if (entity.getEndereco() != null) dto.setIdEndereco(entity.getEndereco().getId_endereco());
        dto.setNome(entity.getNome());
        dto.setCnpj(entity.getCnpj());

        return dto;
    }

    public static List<ClinicaResponseDto> toResponseDto(List<Clinica> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(ClinicaMapper::toResponseDto).collect(Collectors.toList());
    }
}
