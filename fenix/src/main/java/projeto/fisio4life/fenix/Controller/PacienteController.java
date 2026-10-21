package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.pacienteDto.PacienteRequestDto;
import projeto.fisio4life.fenix.Dto.pacienteDto.PacienteResponseDto;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Mapper.PacienteMapper;
import projeto.fisio4life.fenix.Service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDto> salvar(@Valid @RequestBody PacienteRequestDto dto) {
        Paciente salvo = service.salvar(PacienteMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(PacienteMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDto>> listarTodos() {
        List<Paciente> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(PacienteMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(PacienteMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody PacienteRequestDto dto) {
        Paciente atualizado = service.atualizar(id, PacienteMapper.toEntity(dto));
        return ResponseEntity.ok(PacienteMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
