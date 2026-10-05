package projeto.fisio4life.fenix.Controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.enderecoDto.EnderecoRequestDto;
import projeto.fisio4life.fenix.Dto.enderecoDto.EnderecoResponseDto;
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Mapper.EnderecoMapper;
import projeto.fisio4life.fenix.Service.EnderecoService;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> salvar(
            @Valid @RequestBody EnderecoRequestDto dto) {

        Endereco entity = EnderecoMapper.toEntity(dto);
        Endereco salvo = service.salvar(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(EnderecoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDto>> listarTodos() {

        List<Endereco> entities = service.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EnderecoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> buscarPorId(
            @PathVariable Integer id) {

        Endereco entity = service.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EnderecoMapper.toResponseDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EnderecoRequestDto dto) {

        Endereco entity = EnderecoMapper.toEntity(dto);
        Endereco atualizado = service.atualizar(id, entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EnderecoMapper.toResponseDto(atualizado));
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