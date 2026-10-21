package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Repository.PacienteRepository;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
private final UsuarioRepository usuarioRepository;

    public PacienteService(PacienteRepository pacienteRepository, UsuarioRepository usuarioRepository) {
        this.pacienteRepository = pacienteRepository;
this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Paciente salvar(Paciente entity) {
        validar(entity, null);

        return pacienteRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Paciente buscarPorId(Integer id) {
        return pacienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Transactional
    public Paciente atualizar(Integer id, Paciente entity) {
        Paciente existente = buscarPorId(id);
        validar(entity, id);
        existente.setCpf(entity.getCpf());
existente.setPermite_atendimento_grupo(entity.getPermite_atendimento_grupo());
existente.setUsuario(entity.getUsuario());
        return pacienteRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Paciente existente = buscarPorId(id);
        try {
            pacienteRepository.delete(existente);
            pacienteRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Paciente: existem registros relacionados.", ex);
        }
    }

    private void validar(Paciente entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paciente não pode ser nulo.");
        if (entity.getUsuario() == null || entity.getUsuario().getId_usuario() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idUsuario é obrigatório.");
        entity.setUsuario(usuarioRepository.findById(entity.getUsuario().getId_usuario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")));
        if (entity.getCpf() == null || !entity.getCpf().matches("\\d{11}"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF deve conter exatamente 11 dígitos.");
        if (entity.getPermite_atendimento_grupo() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permissão para atendimento em grupo é obrigatória.");
        if (pacienteRepository.existeCpfEmOutroPaciente(entity.getCpf(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado para outro paciente.");
        if (pacienteRepository.existeOutroPacienteParaUsuario(entity.getUsuario().getId_usuario(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O usuário já está vinculado a outro paciente.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
