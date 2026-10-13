package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.evolucaoDto.EvolucaoRequestDto;
import projeto.fisio4life.fenix.Dto.evolucaoDto.EvolucaoResponseDto;
import projeto.fisio4life.fenix.Entity.Evolucao;
import projeto.fisio4life.fenix.Mapper.EvolucaoMapper;
import projeto.fisio4life.fenix.Service.EvolucaoService;

import java.util.List;

@RestController
@RequestMapping("/api/evolucoes")
public class EvolucaoController {

    private final EvolucaoService service;

    public EvolucaoController(EvolucaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EvolucaoResponseDto> salvar(
            @Valid @RequestBody EvolucaoRequestDto dto) {

        Evolucao entity = EvolucaoMapper.toEntity(dto);
        Evolucao salvo = service.salvar(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(EvolucaoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<EvolucaoResponseDto>> listarTodos() {

        List<Evolucao> entities = service.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EvolucaoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvolucaoResponseDto> buscarPorId(
            @PathVariable Integer id) {

        Evolucao entity = service.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EvolucaoMapper.toResponseDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvolucaoResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EvolucaoRequestDto dto) {

        Evolucao entity = EvolucaoMapper.toEntity(dto);
        Evolucao atualizado = service.atualizar(id, entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EvolucaoMapper.toResponseDto(atualizado));
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