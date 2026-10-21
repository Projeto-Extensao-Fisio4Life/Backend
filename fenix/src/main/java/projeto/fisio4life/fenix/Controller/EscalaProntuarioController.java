package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.escalaProntuarioDto.EscalaProntuarioRequestDto;
import projeto.fisio4life.fenix.Dto.escalaProntuarioDto.EscalaProntuarioResponseDto;
import projeto.fisio4life.fenix.Entity.EscalaProntuario;
import projeto.fisio4life.fenix.Mapper.EscalaProntuarioMapper;
import projeto.fisio4life.fenix.Service.EscalaProntuarioService;

@RestController
@RequestMapping("/api/escalas-prontuario")
public class EscalaProntuarioController {

    private final EscalaProntuarioService service;

    public EscalaProntuarioController(EscalaProntuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EscalaProntuarioResponseDto> salvar(@Valid @RequestBody EscalaProntuarioRequestDto dto) {
        EscalaProntuario salvo = service.salvar(EscalaProntuarioMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(EscalaProntuarioMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<EscalaProntuarioResponseDto>> listarTodos() {
        List<EscalaProntuario> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EscalaProntuarioMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscalaProntuarioResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(EscalaProntuarioMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscalaProntuarioResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody EscalaProntuarioRequestDto dto) {
        EscalaProntuario atualizado = service.atualizar(id, EscalaProntuarioMapper.toEntity(dto));
        return ResponseEntity.ok(EscalaProntuarioMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/prontuario/{idProntuario}")
    public ResponseEntity<List<EscalaProntuarioResponseDto>> listarPorProntuario(@PathVariable Integer idProntuario) {
        List<EscalaProntuario> itens = service.listarPorProntuario(idProntuario);
        if (itens.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EscalaProntuarioMapper.toResponseDto(itens));
    }

}
