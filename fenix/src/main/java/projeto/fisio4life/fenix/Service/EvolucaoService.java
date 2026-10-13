package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Evolucao;
import projeto.fisio4life.fenix.Repository.EvolucaoRepository;
import projeto.fisio4life.fenix.Repository.ProntuarioRepository;
import projeto.fisio4life.fenix.Repository.SessaoRepository;

@Service
public class EvolucaoService {

    private final EvolucaoRepository evolucaoRepository;
    private final SessaoRepository sessaoRepository;
    private final ProntuarioRepository prontuarioRepository;

    public EvolucaoService(EvolucaoRepository evolucaoRepository, SessaoRepository sessaoRepository, ProntuarioRepository prontuarioRepository) {
        this.evolucaoRepository = evolucaoRepository;
        this.sessaoRepository = sessaoRepository;
        this.prontuarioRepository = prontuarioRepository;
    }

    public Evolucao salvar(Evolucao entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Evolucao não pode ser nulo."
            );
        }


        if (entity.getSessao() == null || entity.getSessao().getId_sessao() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idSessao é obrigatório."
            );
        }

        sessaoRepository.findById(entity.getSessao().getId_sessao())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sessao não encontrado."
                ));


        if (entity.getProntuario() == null || entity.getProntuario().getId_prontuario() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idProntuario é obrigatório."
            );
        }

        prontuarioRepository.findById(entity.getProntuario().getId_prontuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prontuario não encontrado."
                ));

        if (entity.getId_evolucao() == null
                && sessaoRepository.existsById(entity.getSessao().getId_sessao())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A sessão já possui uma evolução."
            );
        }

        return evolucaoRepository.save(entity);
    }

    public Evolucao buscarPorId(Integer id) {
        return evolucaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Evolucao não encontrado."
                ));
    }

    public List<Evolucao> listarTodos() {
        return evolucaoRepository.findAll();
    }

    public Evolucao atualizar(Integer id, Evolucao entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Evolucao não pode ser nulo."
            );
        }

        Evolucao existente = buscarPorId(id);


        if (entity.getSessao() == null || entity.getSessao().getId_sessao() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idSessao é obrigatório."
            );
        }

        sessaoRepository.findById(entity.getSessao().getId_sessao())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sessao não encontrado."
                ));


        if (entity.getProntuario() == null || entity.getProntuario().getId_prontuario() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idProntuario é obrigatório."
            );
        }

        prontuarioRepository.findById(entity.getProntuario().getId_prontuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prontuario não encontrado."
                ));

        if (entity.getId_evolucao() == null
                && sessaoRepository.existsById(entity.getSessao().getId_sessao())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A sessão já possui uma evolução."
            );
        }

        existente.setData_evolucao(entity.getData_evolucao());
        existente.setConduta(entity.getConduta());
        existente.setAvaliacao(entity.getAvaliacao());
        existente.setObservacao(entity.getObservacao());
        existente.setSessao(entity.getSessao());
        existente.setProntuario(entity.getProntuario());

        return evolucaoRepository.save(existente);
    }

    public void excluir(Integer id) {
        Evolucao existente = buscarPorId(id);
        evolucaoRepository.delete(existente);
    }
}