package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Repository.ClinicaRepository;
import projeto.fisio4life.fenix.Repository.ContatoRepository;
import projeto.fisio4life.fenix.Repository.EnderecoRepository;

@Service
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;
    private final ContatoRepository contatoRepository;
    private final EnderecoRepository enderecoRepository;

    public ClinicaService(ClinicaRepository clinicaRepository, ContatoRepository contatoRepository, EnderecoRepository enderecoRepository) {
        this.clinicaRepository = clinicaRepository;
        this.contatoRepository = contatoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Clinica salvar(Clinica entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Clinica não pode ser nulo."
            );
        }


        if (entity.getContato() == null || entity.getContato().getId_contato() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idContato é obrigatório."
            );
        }

        contatoRepository.findById(entity.getContato().getId_contato())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Contato não encontrado."
                ));


        if (entity.getEndereco() == null || entity.getEndereco().getId_endereco() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idEndereco é obrigatório."
            );
        }

        enderecoRepository.findById(entity.getEndereco().getId_endereco())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Endereco não encontrado."
                ));

        if (entity.getCnpj() != null && entity.getId_clinica() == null
                && clinicaRepository.existsByCnpj(entity.getCnpj())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado.");
        }

        return clinicaRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Clinica buscarPorId(Integer id) {
        return clinicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Clinica não encontrado."
                ));
    }

    @Transactional(readOnly = true)
    public List<Clinica> listarTodos() {
        return clinicaRepository.findAll();
    }

    @Transactional
    public Clinica atualizar(Integer id, Clinica entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Clinica não pode ser nulo."
            );
        }

        Clinica existente = buscarPorId(id);


        if (entity.getContato() == null || entity.getContato().getId_contato() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idContato é obrigatório."
            );
        }

        contatoRepository.findById(entity.getContato().getId_contato())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Contato não encontrado."
                ));


        if (entity.getEndereco() == null || entity.getEndereco().getId_endereco() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idEndereco é obrigatório."
            );
        }

        enderecoRepository.findById(entity.getEndereco().getId_endereco())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Endereco não encontrado."
                ));

        if (entity.getCnpj() != null && entity.getId_clinica() == null
                && clinicaRepository.existsByCnpj(entity.getCnpj())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado.");
        }

        existente.setNome(entity.getNome());
        existente.setCnpj(entity.getCnpj());
        existente.setContato(entity.getContato());
        existente.setEndereco(entity.getEndereco());

        return clinicaRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Clinica existente = buscarPorId(id);
        clinicaRepository.delete(existente);
    }
}
