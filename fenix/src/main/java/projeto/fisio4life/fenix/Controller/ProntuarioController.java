package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.prontuarioDto.ProntuarioRequestDto;
import projeto.fisio4life.fenix.Dto.prontuarioDto.ProntuarioResponseDto;
import projeto.fisio4life.fenix.Entity.Prontuario;
import projeto.fisio4life.fenix.Mapper.ProntuarioMapper;
import projeto.fisio4life.fenix.Service.ProntuarioService;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    private final ProntuarioService service;

    public ProntuarioController(ProntuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProntuarioResponseDto> salvar(@Valid @RequestBody ProntuarioRequestDto dto) {
        Prontuario salvo = service.salvar(ProntuarioMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProntuarioMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ProntuarioResponseDto>> listarTodos() {
        List<Prontuario> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ProntuarioMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ProntuarioMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody ProntuarioRequestDto dto) {
        Prontuario atualizado = service.atualizar(id, ProntuarioMapper.toEntity(dto));
        return ResponseEntity.ok(ProntuarioMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
