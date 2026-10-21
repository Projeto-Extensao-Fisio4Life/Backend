package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaRequestDto;
import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaResponseDto;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Mapper.ClinicaMapper;
import projeto.fisio4life.fenix.Service.ClinicaService;

@RestController
@RequestMapping("/api/clinicas")
public class ClinicaController {

    private final ClinicaService service;

    public ClinicaController(ClinicaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClinicaResponseDto> salvar(@Valid @RequestBody ClinicaRequestDto dto) {
        Clinica salvo = service.salvar(ClinicaMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ClinicaMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ClinicaResponseDto>> listarTodos() {
        List<Clinica> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ClinicaMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ClinicaMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody ClinicaRequestDto dto) {
        Clinica atualizado = service.atualizar(id, ClinicaMapper.toEntity(dto));
        return ResponseEntity.ok(ClinicaMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
