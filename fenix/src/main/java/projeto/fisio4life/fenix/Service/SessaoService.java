package projeto.fisio4life.fenix.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Entity.Servico;
import projeto.fisio4life.fenix.Entity.Sessao;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;
import projeto.fisio4life.fenix.Repository.AgendamentoRepository;
import projeto.fisio4life.fenix.Repository.EvolucaoRepository;
import projeto.fisio4life.fenix.Repository.ServicoRepository;
import projeto.fisio4life.fenix.Repository.SessaoRepository;

import java.util.List;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;

    public SessaoService(SessaoRepository sessaoRepository, AgendamentoRepository agendamentoRepository, ServicoRepository servicoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.servicoRepository = servicoRepository;
    }

    @Transactional
    public Sessao salvar(Sessao entity) {
        validar(entity, null);
        return sessaoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Sessao buscarPorId(Integer id) {
        return sessaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessao não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Sessao> listarTodos() {
        return sessaoRepository.findAll();
    }

    @Transactional
    public Sessao atualizar(Integer id, Sessao entity) {
        Sessao existente = buscarPorId(id);
        validar(entity, id);
        existente.setData_sessao(entity.getData_sessao());
        existente.setDescricao(entity.getDescricao());
        existente.setObservacao(entity.getObservacao());
        existente.setAgendamento(entity.getAgendamento());
        existente.setServico(entity.getServico());
        existente.setStatus_sessao(entity.getStatus_sessao());
        existente.setGera_cobranca(entity.getGera_cobranca());

        return sessaoRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Sessao existente = buscarPorId(id);
        try {
            sessaoRepository.delete(existente);
            sessaoRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Sessao: existem registros relacionados.", ex);
        }
    }

    private void validar(Sessao entity, Integer idAtual) {
        Agendamento agendamento = agendamentoRepository.findById(entity.getAgendamento().getId_agendamento())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado."));
        entity.setAgendamento(agendamento);

        Servico servico = servicoRepository.findById(entity.getServico().getId_servico())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado."));
        entity.setServico(servico);

        if (agendamento.getStatus_agendamento().equals(RegraStatusEnum.INATIVO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível registrar sessão para agendamento cancelado.");
        }

        if (servico.getStatus_servico() != RegraStatusEnum.ATIVO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O serviço não está ativo.");
        }

        if (!agendamento.getData_agendamento().equals(entity.getData_sessao().toLocalDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data da sessão deve corresponder à data do agendamento.");
        }

        if (sessaoRepository.existeSessaoDoAgendamento(agendamento.getId_agendamento(), idAtual)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este agendamento já possui uma sessão.");
        }

        Integer pacienteId = agendamento.getPaciente().getId_paciente();
        Integer sessoesNoServico = sessaoRepository.contarSessoesDoPacienteNoServico(pacienteId, servico.getId_servico(), idAtual);
        if (sessoesNoServico >= servico.getQuantidade_sessoes()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O paciente já atingiu a quantidade de sessões contratada para este serviço.");
        }
    }

    @Transactional(readOnly = true)
    public List<Sessao> listarPorServico(Integer idServico) {
        if (servicoRepository.findById(idServico).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado.");
        }
        return sessaoRepository.findAll().stream()
                .filter(s -> s.getServico().getId_servico().equals(idServico))
                .toList();
    }


//    private void validarTexto(String valor, String campo, int max) {
//        if (valor == null || valor.isBlank() || valor.length() > max)
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
//    }

}
