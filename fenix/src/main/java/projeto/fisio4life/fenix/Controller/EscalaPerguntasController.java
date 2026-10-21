package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.escalaPerguntasDto.EscalaPerguntasRequestDto;
import projeto.fisio4life.fenix.Dto.escalaPerguntasDto.EscalaPerguntasResponseDto;
import projeto.fisio4life.fenix.Entity.EscalaPerguntas;
import projeto.fisio4life.fenix.Mapper.EscalaPerguntasMapper;
import projeto.fisio4life.fenix.Service.EscalaPerguntasService;

@RestController
@RequestMapping("/api/escalas-perguntas")
public class EscalaPerguntasController {

    private final EscalaPerguntasService service;

    public EscalaPerguntasController(EscalaPerguntasService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EscalaPerguntasResponseDto> salvar(@Valid @RequestBody EscalaPerguntasRequestDto dto) {
        EscalaPerguntas salvo = service.salvar(EscalaPerguntasMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(EscalaPerguntasMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<EscalaPerguntasResponseDto>> listarTodos() {
        List<EscalaPerguntas> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EscalaPerguntasMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscalaPerguntasResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(EscalaPerguntasMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscalaPerguntasResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody EscalaPerguntasRequestDto dto) {
        EscalaPerguntas atualizado = service.atualizar(id, EscalaPerguntasMapper.toEntity(dto));
        return ResponseEntity.ok(EscalaPerguntasMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/escala/{idEscala}")
    public ResponseEntity<List<EscalaPerguntasResponseDto>> listarPorEscala(@PathVariable Integer idEscala) {
        List<EscalaPerguntas> itens = service.listarPorEscala(idEscala);
        if (itens.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EscalaPerguntasMapper.toResponseDto(itens));
    }

}
