package projeto.fisio4life.fenix.Controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.fisioterapeutaDto.FisioterapeutaRequestDto;
import projeto.fisio4life.fenix.Dto.fisioterapeutaDto.FisioterapeutaResponseDto;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Mapper.FisioterapeutaMapper;
import projeto.fisio4life.fenix.Service.FisioterapeutaService;

import java.util.List;

@RestController
@RequestMapping("/api/fisioterapeutas")
public class FisioterapeutaController {

    private final FisioterapeutaService service;

    public FisioterapeutaController(FisioterapeutaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FisioterapeutaResponseDto> salvar(
            @Valid @RequestBody FisioterapeutaRequestDto dto) {

        Fisioterapeuta entity = FisioterapeutaMapper.toEntity(dto);
        Fisioterapeuta salvo = service.salvar(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(FisioterapeutaMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<FisioterapeutaResponseDto>> listarTodos() {

        List<Fisioterapeuta> entities = service.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FisioterapeutaMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FisioterapeutaResponseDto> buscarPorId(
            @PathVariable Integer id) {

        Fisioterapeuta entity = service.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FisioterapeutaMapper.toResponseDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FisioterapeutaResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody FisioterapeutaRequestDto dto) {

        Fisioterapeuta entity = FisioterapeutaMapper.toEntity(dto);
        Fisioterapeuta atualizado = service.atualizar(id, entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FisioterapeutaMapper.toResponseDto(atualizado));
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