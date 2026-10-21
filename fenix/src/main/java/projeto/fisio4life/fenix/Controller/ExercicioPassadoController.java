package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.exercicioPassado.ExercicioPassadoRequestDto;
import projeto.fisio4life.fenix.Dto.exercicioPassado.ExercicioPassadoResponseDto;
import projeto.fisio4life.fenix.Entity.ExercicioPassado;
import projeto.fisio4life.fenix.Mapper.ExercicioPassadoMapper;
import projeto.fisio4life.fenix.Service.ExercicioPassadoService;

@RestController
@RequestMapping("/api/exercicios-passados")
public class ExercicioPassadoController {

    private final ExercicioPassadoService service;

    public ExercicioPassadoController(ExercicioPassadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExercicioPassadoResponseDto> salvar(@Valid @RequestBody ExercicioPassadoRequestDto dto) {
        ExercicioPassado salvo = service.salvar(ExercicioPassadoMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ExercicioPassadoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ExercicioPassadoResponseDto>> listarTodos() {
        List<ExercicioPassado> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ExercicioPassadoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExercicioPassadoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ExercicioPassadoMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioPassadoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody ExercicioPassadoRequestDto dto) {
        ExercicioPassado atualizado = service.atualizar(id, ExercicioPassadoMapper.toEntity(dto));
        return ResponseEntity.ok(ExercicioPassadoMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/evolucao/{idEvolucao}")
    public ResponseEntity<List<ExercicioPassadoResponseDto>> listarPorEvolucao(@PathVariable Integer idEvolucao) {
        List<ExercicioPassado> itens = service.listarPorEvolucao(idEvolucao);
        if (itens.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ExercicioPassadoMapper.toResponseDto(itens));
    }

}
