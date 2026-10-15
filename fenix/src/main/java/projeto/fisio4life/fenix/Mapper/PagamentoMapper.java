package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.pagamentoDto.PagamentoRequestDto;
import projeto.fisio4life.fenix.Dto.pagamentoDto.PagamentoResponseDto;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Pagamento;
import projeto.fisio4life.fenix.Entity.Servico;

public final class PagamentoMapper {

    private PagamentoMapper() {}

    public static Pagamento toEntity(PagamentoRequestDto dto) {
        if (dto == null) return null;

        Pagamento entity = new Pagamento();

        if (dto.getIdPaciente() != null) {
            Paciente ref = new Paciente();
            ref.setId_paciente(dto.getIdPaciente());
            entity.setPaciente(ref);
        }
        if (dto.getIdServico() != null) {
            Servico ref = new Servico();
            ref.setId_servico(dto.getIdServico());
            entity.setServico(ref);
        }
        entity.setValor(dto.getValor());
        entity.setForma_pagamento(dto.getFormaPagamento());
        entity.setStatus_pagamento(dto.getStatusPagamento() == null ? null : dto.getStatusPagamento().byteValue());
        entity.setData_concluida(dto.getDataConcluida());

        return entity;
    }

    public static PagamentoResponseDto toResponseDto(Pagamento entity) {
        if (entity == null) return null;

        PagamentoResponseDto dto = new PagamentoResponseDto();
        dto.setIdPagamento(entity.getId_pagamento());
        if (entity.getPaciente() != null) dto.setIdPaciente(entity.getPaciente().getId_paciente());
        if (entity.getServico() != null) dto.setIdServico(entity.getServico().getId_servico());
        dto.setValor(entity.getValor());
        dto.setFormaPagamento(entity.getForma_pagamento());
        dto.setStatusPagamento(entity.getStatus_pagamento() == null ? null : entity.getStatus_pagamento().intValue());
        dto.setDataConcluida(entity.getData_concluida());

        return dto;
    }

    public static List<PagamentoResponseDto> toResponseDto(List<Pagamento> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(PagamentoMapper::toResponseDto).collect(Collectors.toList());
    }
}
