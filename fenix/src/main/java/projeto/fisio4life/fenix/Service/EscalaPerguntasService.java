package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.EscalaPerguntas;
import projeto.fisio4life.fenix.Repository.EscalaPerguntasRepository;
import projeto.fisio4life.fenix.Repository.EscalaRepository;

@Service
public class EscalaPerguntasService {

    private final EscalaPerguntasRepository escalaPerguntasRepository;
private final EscalaRepository escalaRepository;

    public EscalaPerguntasService(EscalaPerguntasRepository escalaPerguntasRepository, EscalaRepository escalaRepository) {
        this.escalaPerguntasRepository = escalaPerguntasRepository;
this.escalaRepository = escalaRepository;
    }

    @Transactional
    public EscalaPerguntas salvar(EscalaPerguntas entity) {
        validar(entity, null);

        return escalaPerguntasRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public EscalaPerguntas buscarPorId(Integer id) {
        return escalaPerguntasRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "EscalaPerguntas não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<EscalaPerguntas> listarTodos() {
        return escalaPerguntasRepository.findAll();
    }

    @Transactional
    public EscalaPerguntas atualizar(Integer id, EscalaPerguntas entity) {
        EscalaPerguntas existente = buscarPorId(id);
        validar(entity, id);
        existente.setTitulo_pergunta(entity.getTitulo_pergunta());
existente.setTipo_dado(entity.getTipo_dado());
existente.setEscala(entity.getEscala());
        return escalaPerguntasRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        EscalaPerguntas existente = buscarPorId(id);
        try {
            escalaPerguntasRepository.delete(existente);
            escalaPerguntasRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir EscalaPerguntas: existem registros relacionados.", ex);
        }
    }

    private void validar(EscalaPerguntas entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EscalaPerguntas não pode ser nulo.");
        if (entity.getEscala() == null || entity.getEscala().getId_escala() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idEscala é obrigatório.");
        entity.setEscala(escalaRepository.findById(entity.getEscala().getId_escala()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escala não encontrada.")));
        validarTexto(entity.getTitulo_pergunta(), "Título da pergunta", 45);
        validarTexto(entity.getTipo_dado(), "Tipo de dado", 45);
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

    @Transactional(readOnly = true)
    public List<EscalaPerguntas> listarPorEscala(Integer idEscala) {
        if (!escalaRepository.existsById(idEscala))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Escala não encontrada.");
        return escalaPerguntasRepository.buscarPorEscala(idEscala);
    }

}
