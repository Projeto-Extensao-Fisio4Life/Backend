package projeto.fisio4life.fenix.Repository;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Usuario;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final ContatoRepository contatoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository, ContatoRepository contatoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.contatoRepository = contatoRepository;
    }

    public Usuario salvar(Usuario entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Usuario não pode ser nulo."
            );
        }


        if (entity.getNome() != null && entity.getId_usuario() == null
                && usuarioRepository.existsByNome(entity.getNome())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de usuário já cadastrado.");
        }

        if (entity.getEndereco() == null || entity.getContato() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço e contato são obrigatórios.");
        }

        entity.setEndereco(enderecoRepository.save(entity.getEndereco()));
        entity.setContato(contatoRepository.save(entity.getContato()));

        return usuarioRepository.save(entity);
    }

     public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario não encontrado."
                ));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(Integer id, Usuario entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Usuario não pode ser nulo."
            );
        }

        Usuario existente = buscarPorId(id);


        if (entity.getNome() != null && entity.getId_usuario() == null
                && usuarioRepository.existsByNome(entity.getNome())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de usuário já cadastrado.");
        }

        if (entity.getEndereco() == null || entity.getContato() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço e contato são obrigatórios.");
        }

        entity.setEndereco(enderecoRepository.save(entity.getEndereco()));
        entity.setContato(contatoRepository.save(entity.getContato()));

        existente.setNome(entity.getNome());
        existente.setData_nascimento(entity.getData_nascimento());
        existente.setSenha(entity.getSenha());
        existente.setTipo_usuario(entity.getTipo_usuario());
        existente.setStatus_usuario(entity.getStatus_usuario());
        if (entity.getEndereco() != null) { existente.setEndereco(enderecoRepository.save(entity.getEndereco())); }
        if (entity.getContato() != null) { existente.setContato(contatoRepository.save(entity.getContato())); }

        return usuarioRepository.save(existente);
    }

    public void excluir(Integer id) {
        Usuario existente = buscarPorId(id);
        usuarioRepository.delete(existente);
    }
}
