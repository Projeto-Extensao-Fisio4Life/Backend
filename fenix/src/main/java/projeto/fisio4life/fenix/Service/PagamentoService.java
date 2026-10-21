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

    @Transactional
    public Pagamento salvar(Pagamento entity) {
        validar(entity, null);

        return pagamentoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Pagamento buscarPorId(Integer id) {
        return pagamentoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    @Transactional
    public Pagamento atualizar(Integer id, Pagamento entity) {
        Pagamento existente = buscarPorId(id);
        validar(entity, id);
        existente.setValor(entity.getValor());
existente.setForma_pagamento(entity.getForma_pagamento());
existente.setStatus_pagamento(entity.getStatus_pagamento());
existente.setData_concluida(entity.getData_concluida());
existente.setPaciente(entity.getPaciente());
existente.setServico(entity.getServico());
        return pagamentoRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Pagamento existente = buscarPorId(id);
        try {
            pagamentoRepository.delete(existente);
            pagamentoRepository.flush();
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

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

    @Transactional(readOnly = true)
    public List<Pagamento> listarPorPaciente(Integer idPaciente) {
        if (!pacienteRepository.existsById(idPaciente))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.");
        return pagamentoRepository.buscarPorPaciente(idPaciente);
    }

    @Transactional(readOnly = true)
    public List<Pagamento> listarPorServico(Integer idServico) {
        if (!servicoRepository.existsById(idServico))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado.");
        return pagamentoRepository.buscarPorServico(idServico);
    }

}
