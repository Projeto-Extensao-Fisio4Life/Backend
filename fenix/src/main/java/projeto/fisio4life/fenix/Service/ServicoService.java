package projeto.fisio4life.fenix.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Servico;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;
import projeto.fisio4life.fenix.Repository.ServicoRepository;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Transactional
    public Servico salvar(Servico entity) {
        if (servicoRepository.existsByNomeIgnoreCase(entity.getNome())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um serviço cadastrado com este nome.");
        }
        return servicoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Servico buscarPorId(Integer id) {
        return servicoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    @Transactional
    public Servico atualizar(Integer id, Servico entity) {
        Servico existente = buscarPorId(id);

        servicoRepository.findByNomeIgnoreCase(entity.getNome())
                .filter(s -> !s.getId_servico().equals(id))
                .ifPresent(s -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe outro serviço cadastrado com este nome.");
                });

        existente.setNome(entity.getNome());
        existente.setQuantidade_sessoes(entity.getQuantidade_sessoes());
        existente.setTipo(entity.getTipo());
        existente.setStatus_servico(entity.getStatus_servico());
        existente.setValor_sessao(entity.getValor_sessao());
        return servicoRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Servico existente = buscarPorId(id);
        existente.setStatus_servico(RegraStatusEnum.INATIVO);
        servicoRepository.save(existente);
    }

//    @Transactional
//    public void excluir(Integer id) {
//        Servico existente = buscarPorId(id);
//        try {
//            servicoRepository.delete(existente);
//            servicoRepository.flush();
//        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,
//                    "Não é possível excluir Serviço: existem registros relacionados.", ex);
//        }
//    }

}
