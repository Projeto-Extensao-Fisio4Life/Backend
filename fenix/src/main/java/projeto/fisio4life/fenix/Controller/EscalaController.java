package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.escalaDto.EscalaRequestDto;
import projeto.fisio4life.fenix.Dto.escalaDto.EscalaResponseDto;
import projeto.fisio4life.fenix.Entity.Escala;
import projeto.fisio4life.fenix.Mapper.EscalaMapper;
import projeto.fisio4life.fenix.Service.EscalaService;

@RestController
@RequestMapping("/api/escalas")
public class EscalaController {

    private final EscalaService service;

    public EscalaController(EscalaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EscalaResponseDto> salvar(@Valid @RequestBody EscalaRequestDto dto) {
        Escala salvo = service.salvar(EscalaMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(EscalaMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<EscalaResponseDto>> listarTodos() {
        List<Escala> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EscalaMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscalaResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(EscalaMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscalaResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody EscalaRequestDto dto) {
        Escala atualizado = service.atualizar(id, EscalaMapper.toEntity(dto));
        return ResponseEntity.ok(EscalaMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
