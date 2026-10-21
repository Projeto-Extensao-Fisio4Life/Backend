package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.sessaoDto.SessaoRequestDto;
import projeto.fisio4life.fenix.Dto.sessaoDto.SessaoResponseDto;
import projeto.fisio4life.fenix.Entity.Sessao;
import projeto.fisio4life.fenix.Mapper.SessaoMapper;
import projeto.fisio4life.fenix.Service.SessaoService;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoController {

    private final SessaoService service;

    public SessaoController(SessaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SessaoResponseDto> salvar(@Valid @RequestBody SessaoRequestDto dto) {
        Sessao salvo = service.salvar(SessaoMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(SessaoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<SessaoResponseDto>> listarTodos() {
        List<Sessao> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(SessaoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(SessaoMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessaoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody SessaoRequestDto dto) {
        Sessao atualizado = service.atualizar(id, SessaoMapper.toEntity(dto));
        return ResponseEntity.ok(SessaoMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
