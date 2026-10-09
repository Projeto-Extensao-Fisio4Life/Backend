package projeto.fisio4life.fenix.Controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.agendamentoDto.AgendamentoRequestDto;
import projeto.fisio4life.fenix.Dto.agendamentoDto.AgendamentoResponseDto;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Mapper.AgendamentoMapper;
import projeto.fisio4life.fenix.Service.AgendamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> salvar(
            @Valid @RequestBody AgendamentoRequestDto dto) {

        Agendamento entity = AgendamentoMapper.toEntity(dto);
        Agendamento salvo = service.salvar(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AgendamentoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDto>> listarTodos() {

        List<Agendamento> entities = service.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AgendamentoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> buscarPorId(
            @PathVariable Integer id) {

        Agendamento entity = service.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AgendamentoMapper.toResponseDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody AgendamentoRequestDto dto) {

        Agendamento entity = AgendamentoMapper.toEntity(dto);
        Agendamento atualizado = service.atualizar(id, entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AgendamentoMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        service.excluir(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}