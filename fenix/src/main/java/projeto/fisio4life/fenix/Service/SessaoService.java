package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Sessao;
import projeto.fisio4life.fenix.Repository.AgendamentoRepository;
import projeto.fisio4life.fenix.Repository.SessaoRepository;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final AgendamentoRepository agendamentoRepository;

    public SessaoService(SessaoRepository sessaoRepository, AgendamentoRepository agendamentoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public Sessao salvar(Sessao entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sessao não pode ser nulo."
            );
        }


        if (entity.getAgendamento() == null || entity.getAgendamento().getId_agendamento() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idAgendamento é obrigatório."
            );
        }

        agendamentoRepository.findById(entity.getAgendamento().getId_agendamento())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agendamento não encontrado."
                ));



        return sessaoRepository.save(entity);
    }

    public Sessao buscarPorId(Integer id) {
        return sessaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sessao não encontrado."
                ));
    }

    public List<Sessao> listarTodos() {
        return sessaoRepository.findAll();
    }

    public Sessao atualizar(Integer id, Sessao entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sessao não pode ser nulo."
            );
        }

        Sessao existente = buscarPorId(id);


        if (entity.getAgendamento() == null || entity.getAgendamento().getId_agendamento() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idAgendamento é obrigatório."
            );
        }

        agendamentoRepository.findById(entity.getAgendamento().getId_agendamento())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agendamento não encontrado."
                ));



        existente.setData_sessao(entity.getData_sessao());
        existente.setDescricao(entity.getDescricao());
        existente.setObservacao(entity.getObservacao());
        existente.setAgendamento(entity.getAgendamento());

        return sessaoRepository.save(existente);
    }

   public void excluir(Integer id) {
        Sessao existente = buscarPorId(id);
        sessaoRepository.delete(existente);
    }
}