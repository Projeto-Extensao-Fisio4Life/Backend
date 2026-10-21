package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Evolucao;
import projeto.fisio4life.fenix.Repository.EvolucaoRepository;
import projeto.fisio4life.fenix.Repository.ProntuarioRepository;
import projeto.fisio4life.fenix.Repository.SessaoRepository;

@Service
public class EvolucaoService {

    private final EvolucaoRepository evolucaoRepository;
private final ProntuarioRepository prontuarioRepository;
private final SessaoRepository sessaoRepository;

    public EvolucaoService(EvolucaoRepository evolucaoRepository, ProntuarioRepository prontuarioRepository, SessaoRepository sessaoRepository) {
        this.evolucaoRepository = evolucaoRepository;
this.prontuarioRepository = prontuarioRepository;
this.sessaoRepository = sessaoRepository;
    }

    @Transactional
    public Evolucao salvar(Evolucao entity) {
        validar(entity, null);

        return evolucaoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Evolucao buscarPorId(Integer id) {
        return evolucaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Evolucao não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Evolucao> listarTodos() {
        return evolucaoRepository.findAll();
    }

    @Transactional
    public Evolucao atualizar(Integer id, Evolucao entity) {
        Evolucao existente = buscarPorId(id);
        validar(entity, id);
        existente.setData_evolucao(entity.getData_evolucao());
existente.setConduta(entity.getConduta());
existente.setAvaliacao(entity.getAvaliacao());
existente.setObservacao(entity.getObservacao());
existente.setSessao(entity.getSessao());
existente.setProntuario(entity.getProntuario());
        return evolucaoRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Evolucao existente = buscarPorId(id);
        try {
            evolucaoRepository.delete(existente);
            evolucaoRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Evolucao: existem registros relacionados.", ex);
        }
    }

    private void validar(Evolucao entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evolucao não pode ser nulo.");
        if (entity.getSessao() == null || entity.getSessao().getId_sessao() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idSessao é obrigatório.");
        entity.setSessao(sessaoRepository.findById(entity.getSessao().getId_sessao()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada.")));
        if (entity.getProntuario() == null || entity.getProntuario().getId_prontuario() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idProntuario é obrigatório.");
        entity.setProntuario(prontuarioRepository.findById(entity.getProntuario().getId_prontuario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuário não encontrado.")));
        Integer idPacienteSessao = entity.getSessao().getAgendamento().getPaciente().getId_paciente();
        Integer idPacienteProntuario = entity.getProntuario().getPaciente().getId_paciente();
        if (!idPacienteSessao.equals(idPacienteProntuario))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A sessão e o prontuário devem pertencer ao mesmo paciente.");
        if (entity.getData_evolucao() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data da evolução é obrigatória.");
        validarTexto(entity.getConduta(), "Conduta", 255);
        validarTexto(entity.getAvaliacao(), "Avaliação", 255);
        validarTexto(entity.getObservacao(), "Observação", 255);
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

    @Transactional(readOnly = true)
    public List<Evolucao> listarPorSessao(Integer idSessao) {
        if (!sessaoRepository.existsById(idSessao))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada.");
        return evolucaoRepository.buscarPorSessao(idSessao);
    }

    @Transactional(readOnly = true)
    public List<Evolucao> listarPorProntuario(Integer idProntuario) {
        if (!prontuarioRepository.existsById(idProntuario))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuário não encontrado.");
        return evolucaoRepository.buscarPorProntuario(idProntuario);
    }

}
