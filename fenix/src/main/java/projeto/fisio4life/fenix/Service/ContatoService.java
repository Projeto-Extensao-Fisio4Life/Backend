package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Contato;
import projeto.fisio4life.fenix.Repository.ContatoRepository;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }


    public Contato salvar(Contato entity) {
        validar(entity, null);

        return contatoRepository.save(entity);
    }


    public Contato buscarPorId(Integer id) {
        return contatoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado."));
    }


    public List<Contato> listarTodos() {
        return contatoRepository.findAll();
    }


    public Contato atualizar(Integer id, Contato entity) {
        Contato contato = buscarPorId(id);
        validar(entity, id);
        contato.setTelefone(entity.getTelefone());
        contato.setCelular(entity.getCelular());
        contato.setEmail(entity.getEmail());
        return contatoRepository.save(contato);
    }


    public void excluir(Integer id) {
        Contato contato = buscarPorId(id);
        try {
            contatoRepository.delete(contato);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Contato: existem registros relacionados.", ex);
        }
    }

    private void validar(Contato entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contato não pode ser nulo.");
        validarTexto(entity.getTelefone(), "Telefone", 14);
        validarTexto(entity.getCelular(), "Celular", 14);
        validarTexto(entity.getEmail(), "E-mail", 45);
        if (!entity.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail inválido.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
