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
        validar(entity, null);

        return clinicaRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Clinica buscarPorId(Integer id) {
        return clinicaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinica não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Clinica> listarTodos() {
        return clinicaRepository.findAll();
    }

    @Transactional
    public Clinica atualizar(Integer id, Clinica entity) {
        Clinica existente = buscarPorId(id);
        validar(entity, id);
        existente.setNome(entity.getNome());
existente.setCnpj(entity.getCnpj());
existente.setContato(entity.getContato());
existente.setEndereco(entity.getEndereco());
        return clinicaRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Clinica existente = buscarPorId(id);
        try {
            clinicaRepository.delete(existente);
            clinicaRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Clinica: existem registros relacionados.", ex);
        }
    }

    private void validar(Clinica entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clinica não pode ser nulo.");
        if (entity.getContato() == null || entity.getContato().getId_contato() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idContato é obrigatório.");
        entity.setContato(contatoRepository.findById(entity.getContato().getId_contato()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado.")));
        if (entity.getEndereco() == null || entity.getEndereco().getId_endereco() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idEndereco é obrigatório.");
        entity.setEndereco(enderecoRepository.findById(entity.getEndereco().getId_endereco()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.")));
        if (entity.getNome() == null || entity.getNome().isBlank() || entity.getNome().length() > 255)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome da clínica é obrigatório e deve ter até 255 caracteres.");
        if (entity.getCnpj() == null || !entity.getCnpj().matches("\\d{14}"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CNPJ deve conter exatamente 14 dígitos.");
        if (clinicaRepository.existeCnpjEmOutra(entity.getCnpj(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado para outra clínica.");
        if (clinicaRepository.existeOutraClinicaNoEndereco(entity.getEndereco().getId_endereco(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O endereço já está vinculado a outra clínica.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
