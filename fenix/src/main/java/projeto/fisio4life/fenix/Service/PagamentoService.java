package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Pagamento;
import projeto.fisio4life.fenix.Repository.PacienteRepository;
import projeto.fisio4life.fenix.Repository.PagamentoRepository;
import projeto.fisio4life.fenix.Repository.ServicoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
private final PacienteRepository pacienteRepository;
private final ServicoRepository servicoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PacienteRepository pacienteRepository, ServicoRepository servicoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pacienteRepository = pacienteRepository;
        this.servicoRepository = servicoRepository;
    }

    public Pagamento salvar(Pagamento entity) {
        validar(entity, null);

        return pagamentoRepository.save(entity);
    }

    public Pagamento buscarPorId(Integer id) {
        return pagamentoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado."));
    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento atualizar(Integer id, Pagamento entity) {
        Pagamento pagamento = buscarPorId(id);
        validar(entity, id);
        pagamento.setValor(entity.getValor());
        pagamento.setForma_pagamento(entity.getForma_pagamento());
        pagamento.setStatus_pagamento(entity.getStatus_pagamento());
        pagamento.setData_concluida(entity.getData_concluida());
        pagamento.setPaciente(entity.getPaciente());
        pagamento.setServico(entity.getServico());
        return pagamentoRepository.save(pagamento);
    }

    public void excluir(Integer id) {
        Pagamento pagamento = buscarPorId(id);
        try {
            pagamentoRepository.delete(pagamento);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Pagamento: existem registros relacionados.", ex);
        }
    }

    private void validar(Pagamento entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pagamento não pode ser nulo.");
        if (entity.getPaciente() != null && entity.getPaciente().getId_paciente() != null)
            entity.setPaciente(pacienteRepository.findById(entity.getPaciente().getId_paciente()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.")));
        else entity.setPaciente(null);
        if (entity.getServico() != null && entity.getServico().getId_servico() != null)
            entity.setServico(servicoRepository.findById(entity.getServico().getId_servico()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado.")));
        else entity.setServico(null);
        if (entity.getValor() != null && entity.getValor().signum() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor do pagamento não pode ser negativo.");
        if (entity.getForma_pagamento() != null && entity.getForma_pagamento().length() > 45)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Forma de pagamento deve ter até 45 caracteres.");
        if (entity.getData_concluida() != null && entity.getData_concluida().length() > 45)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data concluída deve ter até 45 caracteres.");
    }


}
