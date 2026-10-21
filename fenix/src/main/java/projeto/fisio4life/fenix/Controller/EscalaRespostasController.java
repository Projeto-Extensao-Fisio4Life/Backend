package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.escalaRespostasDto.EscalaRespostasRequestDto;
import projeto.fisio4life.fenix.Dto.escalaRespostasDto.EscalaRespostasResponseDto;
import projeto.fisio4life.fenix.Entity.EscalaRespostas;
import projeto.fisio4life.fenix.Mapper.EscalaRespostasMapper;
import projeto.fisio4life.fenix.Service.EscalaRespostasService;

@RestController
@RequestMapping("/api/escalas-respostas")
public class EscalaRespostasController {

    private final EscalaRespostasService service;

    public EscalaRespostasController(EscalaRespostasService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EscalaRespostasResponseDto> salvar(@Valid @RequestBody EscalaRespostasRequestDto dto) {
        EscalaRespostas salvo = service.salvar(EscalaRespostasMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(EscalaRespostasMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<EscalaRespostasResponseDto>> listarTodos() {
        List<EscalaRespostas> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EscalaRespostasMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscalaRespostasResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(EscalaRespostasMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscalaRespostasResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody EscalaRespostasRequestDto dto) {
        EscalaRespostas atualizado = service.atualizar(id, EscalaRespostasMapper.toEntity(dto));
        return ResponseEntity.ok(EscalaRespostasMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aplicacao/{idEscalaProntuario}")
    public ResponseEntity<List<EscalaRespostasResponseDto>> listarPorAplicacao(@PathVariable Integer idEscalaProntuario) {
        List<EscalaRespostas> itens = service.listarPorAplicacao(idEscalaProntuario);
        if (itens.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EscalaRespostasMapper.toResponseDto(itens));
    }

}
