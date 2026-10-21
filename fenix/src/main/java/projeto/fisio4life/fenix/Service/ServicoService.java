package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Servico;
import projeto.fisio4life.fenix.Repository.ServicoRepository;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Transactional
    public Servico salvar(Servico entity) {
        validar(entity, null);

        return servicoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Servico buscarPorId(Integer id) {
        return servicoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    @Transactional
    public Servico atualizar(Integer id, Servico entity) {
        Servico existente = buscarPorId(id);
        validar(entity, id);
        existente.setNome(entity.getNome());
existente.setQuantidade_sessoes(entity.getQuantidade_sessoes());
existente.setTipo(entity.getTipo());
existente.setStatus_servico(entity.getStatus_servico());
        return servicoRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Servico existente = buscarPorId(id);
        try {
            servicoRepository.delete(existente);
            servicoRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Servico: existem registros relacionados.", ex);
        }
    }

    private void validar(Servico entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Servico não pode ser nulo.");
        if (entity.getNome() == null || entity.getNome().isBlank() || entity.getNome().length() > 45)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do serviço é obrigatório e deve ter até 45 caracteres.");
        if (entity.getQuantidade_sessoes() == null || entity.getQuantidade_sessoes() <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de sessões deve ser maior que zero.");
        if (entity.getTipo() == null || entity.getTipo().isBlank() || entity.getTipo().length() > 20)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo do serviço é obrigatório e deve ter até 20 caracteres.");
        if (entity.getStatus_servico() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status do serviço é obrigatório.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
