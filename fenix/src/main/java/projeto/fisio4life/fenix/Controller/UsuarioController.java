package projeto.fisio4life.fenix.Controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioRequestDto;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioResponseDto;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Mapper.UsuarioMapper;
import projeto.fisio4life.fenix.Repository.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> salvar(
            @Valid @RequestBody UsuarioRequestDto dto) {

        Usuario entity = UsuarioMapper.toEntity(dto);
        Usuario salvo = service.salvar(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UsuarioMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarTodos() {

        List<Usuario> entities = service.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UsuarioMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(
            @PathVariable Integer id) {

        Usuario entity = service.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UsuarioMapper.toResponseDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioRequestDto dto) {

        Usuario entity = UsuarioMapper.toEntity(dto);
        Usuario atualizado = service.atualizar(id, entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UsuarioMapper.toResponseDto(atualizado));
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