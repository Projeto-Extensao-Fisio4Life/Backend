package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.evolucaoDto.EvolucaoRequestDto;
import projeto.fisio4life.fenix.Dto.evolucaoDto.EvolucaoResponseDto;
import projeto.fisio4life.fenix.Entity.Evolucao;
import projeto.fisio4life.fenix.Mapper.EvolucaoMapper;
import projeto.fisio4life.fenix.Service.EvolucaoService;

@RestController
@RequestMapping("/api/evolucoes")
public class EvolucaoController {

    private final EvolucaoService service;

    public EvolucaoController(EvolucaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EvolucaoResponseDto> salvar(@Valid @RequestBody EvolucaoRequestDto dto) {
        Evolucao salvo = service.salvar(EvolucaoMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(EvolucaoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<EvolucaoResponseDto>> listarTodos() {
        List<Evolucao> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EvolucaoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvolucaoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(EvolucaoMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvolucaoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody EvolucaoRequestDto dto) {
        Evolucao atualizado = service.atualizar(id, EvolucaoMapper.toEntity(dto));
        return ResponseEntity.ok(EvolucaoMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sessao/{idSessao}")
    public ResponseEntity<List<EvolucaoResponseDto>> listarPorSessao(@PathVariable Integer idSessao) {
        List<Evolucao> itens = service.listarPorSessao(idSessao);
        if (itens.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EvolucaoMapper.toResponseDto(itens));
    }

    @GetMapping("/prontuario/{idProntuario}")
    public ResponseEntity<List<EvolucaoResponseDto>> listarPorProntuario(@PathVariable Integer idProntuario) {
        List<Evolucao> itens = service.listarPorProntuario(idProntuario);
        if (itens.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(EvolucaoMapper.toResponseDto(itens));
    }

}
