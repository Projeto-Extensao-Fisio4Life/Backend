package projeto.fisio4life.fenix.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Repository.PacienteRepository;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioService usuarioService;

    public PacienteService(PacienteRepository pacienteRepository, UsuarioService usuarioService) {
        this.pacienteRepository = pacienteRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public Paciente salvar(Paciente entity) {
        validar(entity, null);
        Usuario usuarioSalvo = usuarioService.salvar(entity.getUsuario());
        entity.setUsuario(usuarioSalvo);
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

        usuarioService.atualizar(existente.getUsuario().getId_usuario(), entity.getUsuario());
        existente.setCpf(entity.getCpf());
        existente.setPermite_atendimento_grupo(entity.getPermite_atendimento_grupo());

        return pacienteRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Paciente existente = buscarPorId(id);
        usuarioService.excluir(existente.getUsuario().getId_usuario());
    }

//    @Transactional
//    public void excluir(Integer id) {
//        Paciente existente = buscarPorId(id);
//        try {
//            pacienteRepository.delete(existente);
//            pacienteRepository.flush();
//        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,
//                    "Não é possível excluir Paciente: existem registros relacionados.", ex);
//        }
//    }

    private void validar(Paciente entity, Integer idAtual) {

        if (pacienteRepository.existeCpfEmOutroPaciente(entity.getCpf(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado para outro paciente.");
    }

}
