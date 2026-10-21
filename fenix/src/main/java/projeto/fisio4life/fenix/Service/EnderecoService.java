package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Repository.EnderecoRepository;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Endereco salvar(Endereco entity) {
        validar(entity, null);

        return enderecoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Endereco buscarPorId(Integer id) {
        return enderecoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    @Transactional
    public Endereco atualizar(Integer id, Endereco entity) {
        Endereco existente = buscarPorId(id);
        validar(entity, id);
        existente.setLogradouro(entity.getLogradouro());
existente.setNumero(entity.getNumero());
existente.setBairro(entity.getBairro());
existente.setCidade(entity.getCidade());
existente.setEstado(entity.getEstado());
existente.setCep(entity.getCep());
        return enderecoRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Endereco existente = buscarPorId(id);
        try {
            enderecoRepository.delete(existente);
            enderecoRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Endereco: existem registros relacionados.", ex);
        }
    }

    private void validar(Endereco entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereco não pode ser nulo.");
        validarTexto(entity.getLogradouro(), "Logradouro", 45);
        validarTexto(entity.getBairro(), "Bairro", 45);
        validarTexto(entity.getCidade(), "Cidade", 45);
        validarTexto(entity.getEstado(), "Estado", 2);
        validarTexto(entity.getCep(), "CEP", 45);
        if (entity.getNumero() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número é obrigatório.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
