package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.contatoDto.ContatoRequestDto;
import projeto.fisio4life.fenix.Dto.contatoDto.ContatoResponseDto;
import projeto.fisio4life.fenix.Entity.Contato;
import projeto.fisio4life.fenix.Mapper.ContatoMapper;
import projeto.fisio4life.fenix.Service.ContatoService;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    private final ContatoService service;

    public ContatoController(ContatoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContatoResponseDto> salvar(@Valid @RequestBody ContatoRequestDto dto) {
        Contato salvo = service.salvar(ContatoMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ContatoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ContatoResponseDto>> listarTodos() {
        List<Contato> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ContatoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ContatoMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody ContatoRequestDto dto) {
        Contato atualizado = service.atualizar(id, ContatoMapper.toEntity(dto));
        return ResponseEntity.ok(ContatoMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
