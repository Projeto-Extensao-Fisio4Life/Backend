package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Servico;
import projeto.fisio4life.fenix.Repository.ClinicaRepository;
import projeto.fisio4life.fenix.Repository.ServicoRepository;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ClinicaRepository clinicaRepository;

    public ServicoService(ServicoRepository servicoRepository, ClinicaRepository clinicaRepository) {
        this.servicoRepository = servicoRepository;
        this.clinicaRepository = clinicaRepository;
    }

    public Servico salvar(Servico entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Servico não pode ser nulo."
            );
        }


        if (entity.getClinica() == null || entity.getClinica().getId_clinica() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idClinica é obrigatório."
            );
        }

        clinicaRepository.findById(entity.getClinica().getId_clinica())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Clinica não encontrado."
                ));

        if (entity.getQuantidade_sessoes() == null || entity.getQuantidade_sessoes() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A quantidade de sessões deve ser maior que zero."
            );
        }

        if (entity.getValor() == null || entity.getValor() < 0.0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O valor do serviço deve ser maior ou igual a zero."
            );
        }

        return servicoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Servico buscarPorId(Integer id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Servico não encontrado."
                ));
    }

    @Transactional(readOnly = true)
    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    @Transactional
    public Servico atualizar(Integer id, Servico entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Servico não pode ser nulo."
            );
        }

        Servico existente = buscarPorId(id);


        if (entity.getClinica() == null || entity.getClinica().getId_clinica() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idClinica é obrigatório."
            );
        }

        clinicaRepository.findById(entity.getClinica().getId_clinica())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Clinica não encontrado."
                ));

        if (entity.getQuantidade_sessoes() == null || entity.getQuantidade_sessoes() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A quantidade de sessões deve ser maior que zero."
            );
        }

        if (entity.getValor() == null || entity.getValor() < 0.0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O valor do serviço deve ser maior ou igual a zero."
            );
        }

        existente.setNome(entity.getNome());
        existente.setQuantidade_sessoes(entity.getQuantidade_sessoes());
        existente.setValor(entity.getValor());
        existente.setTipo(entity.getTipo());
        existente.setClinica(entity.getClinica());

        return servicoRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Servico existente = buscarPorId(id);
        servicoRepository.delete(existente);
    }
}