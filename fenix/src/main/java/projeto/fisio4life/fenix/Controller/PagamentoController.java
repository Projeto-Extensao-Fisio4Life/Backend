package projeto.fisio4life.fenix.Controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.pagamentoDto.PagamentoRequestDto;
import projeto.fisio4life.fenix.Dto.pagamentoDto.PagamentoResponseDto;
import projeto.fisio4life.fenix.Entity.Pagamento;
import projeto.fisio4life.fenix.Mapper.PagamentoMapper;
import projeto.fisio4life.fenix.Service.PagamentoService;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDto> salvar(@Valid @RequestBody PagamentoRequestDto dto) {
        Pagamento salvo = service.salvar(PagamentoMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(PagamentoMapper.toResponseDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDto>> listarTodos() {
        List<Pagamento> entities = service.listarTodos();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(PagamentoMapper.toResponseDto(entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(PagamentoMapper.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody PagamentoRequestDto dto) {
        Pagamento atualizado = service.atualizar(id, PagamentoMapper.toEntity(dto));
        return ResponseEntity.ok(PagamentoMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<PagamentoResponseDto>> listarPorPaciente(@PathVariable Integer idPaciente) {
        List<Pagamento> itens = service.listarPorPaciente(idPaciente);
        if (itens.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(PagamentoMapper.toResponseDto(itens));
    }

    @GetMapping("/servico/{idServico}")
    public ResponseEntity<List<PagamentoResponseDto>> listarPorServico(@PathVariable Integer idServico) {
        List<Pagamento> itens = service.listarPorServico(idServico);
        if (itens.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(PagamentoMapper.toResponseDto(itens));
    }

}
