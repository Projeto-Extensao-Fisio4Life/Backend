package projeto.fisio4life.fenix.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Repository.ContatoRepository;
import projeto.fisio4life.fenix.Repository.EnderecoRepository;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ContatoRepository contatoRepository;
    private final EnderecoRepository enderecoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, ContatoRepository contatoRepository, EnderecoRepository enderecoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.contatoRepository = contatoRepository;
        this.enderecoRepository = enderecoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario salvar(Usuario entity) {
        validar(entity, null);
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
        validar(entity, id);
        existente.setNome(entity.getNome());
        existente.setData_nascimento(entity.getData_nascimento());
        existente.setSenha(passwordEncoder.encode(entity.getSenha()));
        existente.setTipo_usuario(entity.getTipo_usuario());
        existente.setStatus_usuario(entity.getStatus_usuario());
        existente.setEndereco(entity.getEndereco());
        existente.setContato(entity.getContato());
        return usuarioRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Usuario existente = buscarPorId(id);
        try {
            usuarioRepository.delete(existente);
            usuarioRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Usuario: existem registros relacionados.", ex);
        }
    }

    private void validar(Usuario entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não pode ser nulo.");
        if (entity.getNome() == null || entity.getNome().isBlank() || entity.getNome().length() > 255)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome é obrigatório e deve ter até 255 caracteres.");
        if (entity.getData_nascimento() == null || !entity.getData_nascimento().isBefore(java.time.LocalDate.now()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento deve ser anterior à data atual.");
        if (entity.getSenha() == null || entity.getSenha().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha é obrigatória.");
        if (entity.getTipo_usuario() == null || entity.getTipo_usuario().toString().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de usuário é obrigatório.");
        if (entity.getStatus_usuario() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status do usuário é obrigatório.");
        if (entity.getEndereco() == null || entity.getEndereco().getId_endereco() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idEndereco é obrigatório.");
        if (entity.getContato() == null || entity.getContato().getId_contato() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idContato é obrigatório.");
        entity.setEndereco(enderecoRepository.findById(entity.getEndereco().getId_endereco()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.")));
        entity.setContato(contatoRepository.findById(entity.getContato().getId_contato()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado.")));
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
