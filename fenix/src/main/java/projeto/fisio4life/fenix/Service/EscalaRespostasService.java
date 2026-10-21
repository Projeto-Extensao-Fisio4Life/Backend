package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.EscalaRespostas;
import projeto.fisio4life.fenix.Repository.EscalaPerguntasRepository;
import projeto.fisio4life.fenix.Repository.EscalaProntuarioRepository;
import projeto.fisio4life.fenix.Repository.EscalaRespostasRepository;

@Service
public class EscalaRespostasService {

    private final EscalaRespostasRepository escalaRespostasRepository;
private final EscalaPerguntasRepository escalaPerguntasRepository;
private final EscalaProntuarioRepository escalaProntuarioRepository;

    public EscalaRespostasService(EscalaRespostasRepository escalaRespostasRepository, EscalaPerguntasRepository escalaPerguntasRepository, EscalaProntuarioRepository escalaProntuarioRepository) {
        this.escalaRespostasRepository = escalaRespostasRepository;
this.escalaPerguntasRepository = escalaPerguntasRepository;
this.escalaProntuarioRepository = escalaProntuarioRepository;
    }

    @Transactional
    public EscalaRespostas salvar(EscalaRespostas entity) {
        validar(entity, null);

        return escalaRespostasRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public EscalaRespostas buscarPorId(Integer id) {
        return escalaRespostasRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "EscalaRespostas não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<EscalaRespostas> listarTodos() {
        return escalaRespostasRepository.findAll();
    }

    @Transactional
    public EscalaRespostas atualizar(Integer id, EscalaRespostas entity) {
        EscalaRespostas existente = buscarPorId(id);
        validar(entity, id);
        existente.setValor_resposta(entity.getValor_resposta());
existente.setEscala_prontuario(entity.getEscala_prontuario());
existente.setEscala_perguntas(entity.getEscala_perguntas());
        return escalaRespostasRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        EscalaRespostas existente = buscarPorId(id);
        try {
            escalaRespostasRepository.delete(existente);
            escalaRespostasRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir EscalaRespostas: existem registros relacionados.", ex);
        }
    }

    private void validar(EscalaRespostas entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EscalaRespostas não pode ser nulo.");
        if (entity.getEscala_prontuario() == null || entity.getEscala_prontuario().getId_escala_prontuario() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idEscalaProntuario é obrigatório.");
        if (entity.getEscala_perguntas() == null || entity.getEscala_perguntas().getId_escala_perguntas() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idEscalaPerguntas é obrigatório.");
        entity.setEscala_prontuario(escalaProntuarioRepository.findById(entity.getEscala_prontuario().getId_escala_prontuario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aplicação da escala não encontrada.")));
        entity.setEscala_perguntas(escalaPerguntasRepository.findById(entity.getEscala_perguntas().getId_escala_perguntas()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pergunta da escala não encontrada.")));
        if (!entity.getEscala_prontuario().getEscala().getId_escala().equals(entity.getEscala_perguntas().getEscala().getId_escala()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A pergunta precisa pertencer à escala respondida.");
        validarTexto(entity.getValor_resposta(), "Valor da resposta", 45);
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

    @Transactional(readOnly = true)
    public List<EscalaRespostas> listarPorAplicacao(Integer idEscalaProntuario) {
        if (!escalaProntuarioRepository.existsById(idEscalaProntuario))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aplicação da escala não encontrada.");
        return escalaRespostasRepository.buscarPorAplicacao(idEscalaProntuario);
    }

}
