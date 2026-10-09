package projeto.fisio4life.fenix.Mapper;

import projeto.fisio4life.fenix.Dto.pacienteClinicaDto.PacienteClinicaRequestDto;
import projeto.fisio4life.fenix.Dto.pacienteClinicaDto.PacienteClinicaResponseDto;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.PacienteClinica;

import java.util.List;
import java.util.stream.Collectors;

public final class PacienteClinicaMapper {

    private PacienteClinicaMapper() {
    }

    public static PacienteClinica toEntity(PacienteClinicaRequestDto dto) {
        if (dto == null) {
            return null;
        }

        PacienteClinica entity = new PacienteClinica();

        entity.setData_cadastro(dto.getDataCadastro());
        entity.setUtlima_atualizacao(dto.getUltimaAtualizacao());
        entity.setStatus_paciente_clinica(dto.getStatusPacienteClinica());
        if (dto.getIdClinica() != null) {
            Clinica clnica = new Clinica();
            clnica.setId_clinica(dto.getIdClinica());
            entity.setClinica(clnica);
        }
        if (dto.getIdPaciente() != null) {
            Paciente paciente = new Paciente();
            paciente.setId_paciente(dto.getIdPaciente());
            entity.setPaciente(paciente);
        }

        return entity;
    }

    public static PacienteClinicaResponseDto toResponseDto(PacienteClinica entity) {
        if (entity == null) {
            return null;
        }

        PacienteClinicaResponseDto dto = new PacienteClinicaResponseDto();

        dto.setIdVinculo(entity.getId_vinculo());
        dto.setDataCadastro(entity.getData_cadastro());
        dto.setUltimaAtualizacao(entity.getUtlima_atualizacao());
        dto.setStatusPacienteClinica(entity.getStatus_paciente_clinica());
        if (entity.getClinica() != null) {
            dto.setIdClinica(entity.getClinica().getId_clinica());
        }
        if (entity.getPaciente() != null) {
            dto.setIdPaciente(entity.getPaciente().getId_paciente());
        }

        return dto;
    }

    public static List<PacienteClinicaResponseDto> toResponseDto(List<PacienteClinica> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(PacienteClinicaMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}