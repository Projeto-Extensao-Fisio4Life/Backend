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
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Contato não pode ser nulo."
            );
        }




        return contatoRepository.save(entity);
    }

   public Contato buscarPorId(Integer id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Contato não encontrado."
                ));
    }

   public List<Contato> listarTodos() {
        return contatoRepository.findAll();
    }

    public Contato atualizar(Integer id, Contato entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Contato não pode ser nulo."
            );
        }

        Contato existente = buscarPorId(id);




        existente.setTelefone(entity.getTelefone());
        existente.setCelular(entity.getCelular());
        existente.setEmail(entity.getEmail());

        return contatoRepository.save(existente);
    }

    public void excluir(Integer id) {
        Contato existente = buscarPorId(id);
        contatoRepository.delete(existente);
    }
}