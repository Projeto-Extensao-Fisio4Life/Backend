package projeto.fisio4life.fenix.Controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.sessaoDto.SessaoRequestDto;
import projeto.fisio4life.fenix.Dto.sessaoDto.SessaoResponseDto;
import projeto.fisio4life.fenix.Entity.Sessao;
import projeto.fisio4life.fenix.Mapper.SessaoMapper;
import projeto.fisio4life.fenix.Service.SessaoService;

import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoController {

    private final SessaoService service;

    public SessaoController(SessaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SessaoResponseDto> salvar(
            @Valid @RequestBody SessaoRequestDto dto) {

        Sessao entity = SessaoMapper.toEntity(dto);
        Sessao salvo = service.salvar(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SessaoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<SessaoResponseDto>> listarTodos() {

        List<Sessao> entities = service.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SessaoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoResponseDto> buscarPorId(
            @PathVariable Integer id) {

        Sessao entity = service.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SessaoMapper.toResponseDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessaoResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody SessaoRequestDto dto) {

        Sessao entity = SessaoMapper.toEntity(dto);
        Sessao atualizado = service.atualizar(id, entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SessaoMapper.toResponseDto(atualizado));
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