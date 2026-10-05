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

   public Endereco salvar(Endereco entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Endereco não pode ser nulo."
            );
        }




        return enderecoRepository.save(entity);
    }

    public Endereco buscarPorId(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Endereco não encontrado."
                ));
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Endereco atualizar(Integer id, Endereco entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Endereco não pode ser nulo."
            );
        }

        Endereco existente = buscarPorId(id);




        existente.setLogradouro(entity.getLogradouro());
        existente.setNumero(entity.getNumero());
        existente.setBairro(entity.getBairro());
        existente.setCidade(entity.getCidade());
        existente.setEstado(entity.getEstado());
        existente.setCep(entity.getCep());

        return enderecoRepository.save(existente);
    }

    public void excluir(Integer id) {
        Endereco existente = buscarPorId(id);
        enderecoRepository.delete(existente);
    }
}

