package projeto.fisio4life.fenix.Controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.pacienteClinicaDto.PacienteClinicaRequestDto;
import projeto.fisio4life.fenix.Dto.pacienteClinicaDto.PacienteClinicaResponseDto;
import projeto.fisio4life.fenix.Entity.PacienteClinica;
import projeto.fisio4life.fenix.Mapper.PacienteClinicaMapper;
import projeto.fisio4life.fenix.Service.PacienteClinicaService;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes-clinicas")
public class PacienteClinicaController {

    private final PacienteClinicaService service;

    public PacienteClinicaController(PacienteClinicaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PacienteClinicaResponseDto> salvar(
            @Valid @RequestBody PacienteClinicaRequestDto dto) {

        PacienteClinica entity = PacienteClinicaMapper.toEntity(dto);
        PacienteClinica salvo = service.salvar(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PacienteClinicaMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<PacienteClinicaResponseDto>> listarTodos() {

        List<PacienteClinica> entities = service.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PacienteClinicaMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteClinicaResponseDto> buscarPorId(
            @PathVariable Integer id) {

        PacienteClinica entity = service.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PacienteClinicaMapper.toResponseDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteClinicaResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody PacienteClinicaRequestDto dto) {

        PacienteClinica entity = PacienteClinicaMapper.toEntity(dto);
        PacienteClinica atualizado = service.atualizar(id, entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PacienteClinicaMapper.toResponseDto(atualizado));
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