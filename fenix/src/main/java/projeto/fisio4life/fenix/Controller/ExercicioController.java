package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.exercicioDto.ExercicioRequestDto;
import projeto.fisio4life.fenix.Dto.exercicioDto.ExercicioResponseDto;
import projeto.fisio4life.fenix.Entity.Exercicio;
import projeto.fisio4life.fenix.Mapper.ExercicioMapper;
import projeto.fisio4life.fenix.Service.ExercicioService;

@RestController
@RequestMapping("/api/exercicios")
public class ExercicioController {

    private final ExercicioService service;

    public ExercicioController(ExercicioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExercicioResponseDto> salvar(@Valid @RequestBody ExercicioRequestDto dto) {
        Exercicio salvo = service.salvar(ExercicioMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ExercicioMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ExercicioResponseDto>> listarTodos() {
        List<Exercicio> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ExercicioMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExercicioResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ExercicioMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody ExercicioRequestDto dto) {
        Exercicio atualizado = service.atualizar(id, ExercicioMapper.toEntity(dto));
        return ResponseEntity.ok(ExercicioMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
