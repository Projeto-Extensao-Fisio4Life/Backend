package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.servicoDto.ServicoRequestDto;
import projeto.fisio4life.fenix.Dto.servicoDto.ServicoResponseDto;
import projeto.fisio4life.fenix.Entity.Servico;
import projeto.fisio4life.fenix.Mapper.ServicoMapper;
import projeto.fisio4life.fenix.Service.ServicoService;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDto> salvar(
            @Valid @RequestBody ServicoRequestDto dto) {

        Servico entity = ServicoMapper.toEntity(dto);
        Servico salvo = service.salvar(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ServicoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDto>> listarTodos() {

        List<Servico> entities = service.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ServicoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> buscarPorId(
            @PathVariable Integer id) {

        Servico entity = service.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ServicoMapper.toResponseDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ServicoRequestDto dto) {

        Servico entity = ServicoMapper.toEntity(dto);
        Servico atualizado = service.atualizar(id, entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ServicoMapper.toResponseDto(atualizado));
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
