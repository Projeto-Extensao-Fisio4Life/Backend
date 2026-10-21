package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioRequestDto;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioResponseDto;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Mapper.UsuarioMapper;
import projeto.fisio4life.fenix.Service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> salvar(@Valid @RequestBody UsuarioRequestDto dto) {
        Usuario salvo = service.salvar(UsuarioMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarTodos() {
        List<Usuario> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(UsuarioMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(UsuarioMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody UsuarioRequestDto dto) {
        Usuario atualizado = service.atualizar(id, UsuarioMapper.toEntity(dto));
        return ResponseEntity.ok(UsuarioMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
