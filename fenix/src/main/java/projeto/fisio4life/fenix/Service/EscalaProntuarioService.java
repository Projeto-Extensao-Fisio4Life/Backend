package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.EscalaProntuario;
import projeto.fisio4life.fenix.Repository.EscalaProntuarioRepository;
import projeto.fisio4life.fenix.Repository.EscalaRepository;
import projeto.fisio4life.fenix.Repository.ProntuarioRepository;

@Service
public class EscalaProntuarioService {

    private final EscalaProntuarioRepository escalaProntuarioRepository;
private final EscalaRepository escalaRepository;
private final ProntuarioRepository prontuarioRepository;

    public EscalaProntuarioService(EscalaProntuarioRepository escalaProntuarioRepository, EscalaRepository escalaRepository, ProntuarioRepository prontuarioRepository) {
        this.escalaProntuarioRepository = escalaProntuarioRepository;
this.escalaRepository = escalaRepository;
this.prontuarioRepository = prontuarioRepository;
    }

    @Transactional
    public EscalaProntuario salvar(EscalaProntuario entity) {
        validar(entity, null);

        return escalaProntuarioRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public EscalaProntuario buscarPorId(Integer id) {
        return escalaProntuarioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "EscalaProntuario não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<EscalaProntuario> listarTodos() {
        return escalaProntuarioRepository.findAll();
    }

    @Transactional
    public EscalaProntuario atualizar(Integer id, EscalaProntuario entity) {
        EscalaProntuario existente = buscarPorId(id);
        validar(entity, id);
        existente.setData_resposta(entity.getData_resposta());
existente.setProntuario(entity.getProntuario());
existente.setEscala(entity.getEscala());
        return escalaProntuarioRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        EscalaProntuario existente = buscarPorId(id);
        try {
            escalaProntuarioRepository.delete(existente);
            escalaProntuarioRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir EscalaProntuario: existem registros relacionados.", ex);
        }
    }

    private void validar(EscalaProntuario entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EscalaProntuario não pode ser nulo.");
        if (entity.getProntuario() == null || entity.getProntuario().getId_prontuario() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idProntuario é obrigatório.");
        if (entity.getEscala() == null || entity.getEscala().getId_escala() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idEscala é obrigatório.");
        entity.setProntuario(prontuarioRepository.findById(entity.getProntuario().getId_prontuario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuário não encontrado.")));
        entity.setEscala(escalaRepository.findById(entity.getEscala().getId_escala()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escala não encontrada.")));
        if (entity.getData_resposta() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data da resposta é obrigatória.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

    @Transactional(readOnly = true)
    public List<EscalaProntuario> listarPorProntuario(Integer idProntuario) {
        if (!prontuarioRepository.existsById(idProntuario))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuário não encontrado.");
        return escalaProntuarioRepository.buscarPorProntuario(idProntuario);
    }

}
