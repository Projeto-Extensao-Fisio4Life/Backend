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
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Paciente não pode ser nulo."
            );
        }


        if (entity.getUsuario() == null || entity.getUsuario().getId_usuario() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idUsuario é obrigatório."
            );
        }

        usuarioRepository.findById(entity.getUsuario().getId_usuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario não encontrado."
                ));

        if (entity.getCpf() != null && entity.getId_paciente() == null
                && pacienteRepository.existsByCpf(entity.getCpf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado.");
        }

        return pacienteRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Paciente buscarPorId(Integer id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paciente não encontrado."
                ));
    }

    @Transactional(readOnly = true)
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Transactional
    public Paciente atualizar(Integer id, Paciente entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Paciente não pode ser nulo."
            );
        }

        Paciente existente = buscarPorId(id);


        if (entity.getUsuario() == null || entity.getUsuario().getId_usuario() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idUsuario é obrigatório."
            );
        }

        usuarioRepository.findById(entity.getUsuario().getId_usuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario não encontrado."
                ));

        if (entity.getCpf() != null && entity.getId_paciente() == null
                && pacienteRepository.existsByCpf(entity.getCpf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado.");
        }

        existente.setCpf(entity.getCpf());
        existente.setPermite_atendimento_grupo(entity.getPermite_atendimento_grupo());
        existente.setUsuario(entity.getUsuario());

        return pacienteRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Paciente existente = buscarPorId(id);
        pacienteRepository.delete(existente);
    }
}