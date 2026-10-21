package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.enderecoDto.EnderecoRequestDto;
import projeto.fisio4life.fenix.Dto.enderecoDto.EnderecoResponseDto;
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Mapper.EnderecoMapper;
import projeto.fisio4life.fenix.Service.EnderecoService;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> salvar(@Valid @RequestBody EnderecoRequestDto dto) {
        Endereco salvo = service.salvar(EnderecoMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(EnderecoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDto>> listarTodos() {
        List<Endereco> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EnderecoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(EnderecoMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody EnderecoRequestDto dto) {
        Endereco atualizado = service.atualizar(id, EnderecoMapper.toEntity(dto));
        return ResponseEntity.ok(EnderecoMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
