package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaRequestDto;
import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaResponseDto;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Entity.Endereco;

public final class ClinicaMapper {

    private ClinicaMapper() {}

    public static Clinica toEntity(ClinicaRequestDto dto) {
        if (dto == null) return null;

        Clinica entity = new Clinica();

        if (dto.getIdEndereco() != null) {
            Endereco ref = new Endereco();
            ref.setId_endereco(dto.getIdEndereco());
            entity.setEndereco(ref);
        }
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        entity.setEmail(dto.getEmail());
        entity.setCelular(dto.getCelular());
        entity.setTelefone(dto.getTelefone());

        return entity;
    }

    public static ClinicaResponseDto toResponseDto(Clinica entity) {
        if (entity == null) return null;

        ClinicaResponseDto dto = new ClinicaResponseDto();
        dto.setIdClinica(entity.getId_clinica());

        dto.setEmail(entity.getEmail());
        dto.setCelular(entity.getCelular());
        dto.setTelefone(entity.getTelefone());

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
