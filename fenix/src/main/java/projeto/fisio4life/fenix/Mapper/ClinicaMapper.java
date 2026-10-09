package projeto.fisio4life.fenix.Mapper;


import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaRequestDto;
import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaResponseDto;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Entity.Contato;
import projeto.fisio4life.fenix.Entity.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public final class ClinicaMapper {

    private ClinicaMapper() {
    }

    public static Clinica toEntity(ClinicaRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Clinica entity = new Clinica();

        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        if (dto.getIdContato() != null) {
            Contato contato = new Contato();
            contato.setId_contato(dto.getIdContato());
            entity.setContato(contato);
        }
        if (dto.getIdEndereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setId_endereco(dto.getIdEndereco());
            entity.setEndereco(endereco);
        }

        return entity;
    }

    public static ClinicaResponseDto toResponseDto(Clinica entity) {
        if (entity == null) {
            return null;
        }

        ClinicaResponseDto dto = new ClinicaResponseDto();

        dto.setIdClinica(entity.getId_clinica());
        dto.setNome(entity.getNome());
        dto.setCnpj(entity.getCnpj());
        if (entity.getContato() != null) {
            dto.setIdContato(entity.getContato().getId_contato());
        }
        if (entity.getEndereco() != null) {
            dto.setIdEndereco(entity.getEndereco().getId_endereco());
        }

        return dto;
    }

    public static List<ClinicaResponseDto> toResponseDto(List<Clinica> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(ClinicaMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}