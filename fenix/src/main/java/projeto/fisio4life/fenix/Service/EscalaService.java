package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Escala;
import projeto.fisio4life.fenix.Repository.EscalaRepository;

@Service
public class EscalaService {

    private final EscalaRepository escalaRepository;

    public EscalaService(EscalaRepository escalaRepository) {
        this.escalaRepository = escalaRepository;
    }

    @Transactional
    public Escala salvar(Escala entity) {
        validar(entity, null);

        return escalaRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Escala buscarPorId(Integer id) {
        return escalaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Escala não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Escala> listarTodos() {
        return escalaRepository.findAll();
    }

    @Transactional
    public Escala atualizar(Integer id, Escala entity) {
        Escala existente = buscarPorId(id);
        validar(entity, id);
        existente.setNome(entity.getNome());
existente.setDescricao(entity.getDescricao());
        return escalaRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Escala existente = buscarPorId(id);
        try {
            escalaRepository.delete(existente);
            escalaRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Escala: existem registros relacionados.", ex);
        }
    }

    private void validar(Escala entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Escala não pode ser nulo.");
        validarTexto(entity.getNome(), "Nome", 45);
        validarTexto(entity.getDescricao(), "Descrição", 45);
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
