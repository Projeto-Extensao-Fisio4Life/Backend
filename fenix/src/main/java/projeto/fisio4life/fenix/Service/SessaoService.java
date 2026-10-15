package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Sessao;
import projeto.fisio4life.fenix.Repository.AgendamentoRepository;
import projeto.fisio4life.fenix.Repository.EvolucaoRepository;
import projeto.fisio4life.fenix.Repository.ServicoRepository;
import projeto.fisio4life.fenix.Repository.SessaoRepository;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;

    public SessaoService(SessaoRepository sessaoRepository, AgendamentoRepository agendamentoRepository, EvolucaoRepository evolucaoRepository, ServicoRepository servicoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.servicoRepository = servicoRepository;
    }

    public Sessao salvar(Sessao entity) {
        validar(entity, null);

        return sessaoRepository.save(entity);
    }

    public Sessao buscarPorId(Integer id) {
        return sessaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessao não encontrado."));
    }

    public List<Sessao> listarTodos() {
        return sessaoRepository.findAll();
    }

    public Sessao atualizar(Integer id, Sessao entity) {
        Sessao sessao = buscarPorId(id);
        validar(entity, id);
        sessao.setData_sessao(entity.getData_sessao());
        sessao.setDescricao(entity.getDescricao());
        sessao.setObservacao(entity.getObservacao());
        sessao.setAgendamento(entity.getAgendamento());
        sessao.setServico(entity.getServico());
        return sessaoRepository.save(sessao);
    }

    public void excluir(Integer id) {
        Sessao sessao = buscarPorId(id);
        try {
            sessaoRepository.delete(sessao);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Sessao: existem registros relacionados.", ex);
        }
    }

    private void validar(Sessao entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessao não pode ser nulo.");
        if (entity.getAgendamento() == null || entity.getAgendamento().getId_agendamento() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idAgendamento é obrigatório.");
        if (entity.getServico() == null || entity.getServico().getId_servico() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idServico é obrigatório.");
        entity.setAgendamento(agendamentoRepository.findById(entity.getAgendamento().getId_agendamento()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado.")));
        entity.setServico(servicoRepository.findById(entity.getServico().getId_servico()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado.")));
        if (entity.getAgendamento().getStatus_agendamento() != null && entity.getAgendamento().getStatus_agendamento().byteValue() == RegraStatus.AGENDAMENTO_CANCELADO)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível registrar sessão para agendamento cancelado.");
        if (entity.getServico().getStatus_servico() == null || entity.getServico().getStatus_servico().byteValue() != RegraStatus.SERVICO_ATIVO)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O serviço não está ativo.");
        if (entity.getData_sessao() == null || !entity.getAgendamento().getData_agendamento().equals(entity.getData_sessao().toLocalDate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data da sessão deve corresponder à data do agendamento.");
        if (entity.getDescricao() == null || entity.getDescricao().isBlank() || entity.getObservacao() == null || entity.getObservacao().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição e observação da sessão são obrigatórias.");
        if (sessaoRepository.existeSessaoDoAgendamento(entity.getAgendamento().getId_agendamento(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este agendamento já possui uma sessão.");
        Integer pacienteId = entity.getAgendamento().getPaciente().getId_paciente();
        Integer sessoesNoServico = sessaoRepository.contarSessoesDoPacienteNoServico(pacienteId, entity.getServico().getId_servico(), idAtual);
        if (sessoesNoServico >= entity.getServico().getQuantidade_sessoes())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O paciente já atingiu a quantidade de sessões contratada para este serviço.");
    }
}
