package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaRequestDto;
import projeto.fisio4life.fenix.Dto.clinicaDto.ClinicaResponseDto;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Mapper.ClinicaMapper;
import projeto.fisio4life.fenix.Service.ClinicaService;

import java.util.List;

@RestController
@RequestMapping("/api/clinicas")
public class ClinicaController {

    private final ClinicaService service;

    public ClinicaController(ClinicaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClinicaResponseDto> salvar(
            @Valid @RequestBody ClinicaRequestDto dto) {

        Clinica entity = ClinicaMapper.toEntity(dto);
        Clinica salvo = service.salvar(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ClinicaMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ClinicaResponseDto>> listarTodos() {

        List<Clinica> entities = service.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ClinicaMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaResponseDto> buscarPorId(
            @PathVariable Integer id) {

        Clinica entity = service.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ClinicaMapper.toResponseDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ClinicaRequestDto dto) {

        Clinica entity = ClinicaMapper.toEntity(dto);
        Clinica atualizado = service.atualizar(id, entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ClinicaMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        service.excluir(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}