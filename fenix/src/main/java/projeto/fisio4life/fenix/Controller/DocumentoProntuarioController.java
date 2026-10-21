package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.documentoProntuarioDto.DocumentoProntuarioRequestDto;
import projeto.fisio4life.fenix.Dto.documentoProntuarioDto.DocumentoProntuarioResponseDto;
import projeto.fisio4life.fenix.Entity.DocumentoProntuario;
import projeto.fisio4life.fenix.Mapper.DocumentoProntuarioMapper;
import projeto.fisio4life.fenix.Service.DocumentoProntuarioService;

@RestController
@RequestMapping("/api/documentos-prontuario")
public class DocumentoProntuarioController {

    private final DocumentoProntuarioService service;

    public DocumentoProntuarioController(DocumentoProntuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DocumentoProntuarioResponseDto> salvar(@Valid @RequestBody DocumentoProntuarioRequestDto dto) {
        DocumentoProntuario salvo = service.salvar(DocumentoProntuarioMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(DocumentoProntuarioMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<DocumentoProntuarioResponseDto>> listarTodos() {
        List<DocumentoProntuario> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(DocumentoProntuarioMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoProntuarioResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(DocumentoProntuarioMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoProntuarioResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody DocumentoProntuarioRequestDto dto) {
        DocumentoProntuario atualizado = service.atualizar(id, DocumentoProntuarioMapper.toEntity(dto));
        return ResponseEntity.ok(DocumentoProntuarioMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/prontuario/{idProntuario}")
    public ResponseEntity<List<DocumentoProntuarioResponseDto>> listarPorProntuario(@PathVariable Integer idProntuario) {
        List<DocumentoProntuario> itens = service.listarPorProntuario(idProntuario);
        if (itens.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(DocumentoProntuarioMapper.toResponseDto(itens));
    }

}
