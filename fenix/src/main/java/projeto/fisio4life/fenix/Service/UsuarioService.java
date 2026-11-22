package projeto.fisio4life.fenix.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Enum.StatusUsuarioEnum;
import projeto.fisio4life.fenix.Repository.EnderecoRepository;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario salvar(Usuario entity) {
        Endereco enderecoSalvo = enderecoRepository.save(entity.getEndereco());
        entity.setEndereco(enderecoSalvo);
        entity.setSenha(passwordEncoder.encode(entity.getSenha()));
        entity.setData_cadastro(LocalDateTime.now());
        return usuarioRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario atualizar(Integer id, Usuario entity) {
        Usuario existente = buscarPorId(id);

        existente.setNome(entity.getNome());
        existente.setData_nascimento(entity.getData_nascimento());
        existente.setEmail(entity.getEmail());
        existente.setCelular(entity.getCelular());
        existente.setTelefone(entity.getTelefone());
        existente.setSenha(passwordEncoder.encode(entity.getSenha()));
        existente.setTipo_usuario(entity.getTipo_usuario());
        existente.setStatus_usuario(entity.getStatus_usuario());

        Endereco enderecoExistente = existente.getEndereco();
        Endereco novosDadosEndereco = entity.getEndereco();
        enderecoExistente.setLogradouro(novosDadosEndereco.getLogradouro());
        enderecoExistente.setNumero(novosDadosEndereco.getNumero());
        enderecoExistente.setBairro(novosDadosEndereco.getBairro());
        enderecoExistente.setCidade(novosDadosEndereco.getCidade());
        enderecoExistente.setEstado(novosDadosEndereco.getEstado());
        enderecoExistente.setCep(novosDadosEndereco.getCep());
        enderecoRepository.save(enderecoExistente);

        return usuarioRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Usuario existente = buscarPorId(id);
        existente.setStatus_usuario(StatusUsuarioEnum.INATIVO);
        usuarioRepository.save(existente);
    }

//    @Transactional
//    public void excluir(Integer id) {
//        Usuario existente = buscarPorId(id);
//        try {
//            usuarioRepository.delete(existente);
//            usuarioRepository.flush();
//        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,
//                    "Não é possível excluir Usuario: existem registros relacionados.", ex);
//        }
//    }

}
