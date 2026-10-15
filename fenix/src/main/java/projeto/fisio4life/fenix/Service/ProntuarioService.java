package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Prontuario;
import projeto.fisio4life.fenix.Repository.PacienteRepository;
import projeto.fisio4life.fenix.Repository.ProntuarioRepository;

@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
private final PacienteRepository pacienteRepository;

    public ProntuarioService(ProntuarioRepository prontuarioRepository, PacienteRepository pacienteRepository) {
        this.prontuarioRepository = prontuarioRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public Prontuario salvar(Prontuario entity) {
        validar(entity, null);

        return prontuarioRepository.save(entity);
    }

    public Prontuario buscarPorId(Integer id) {
        return prontuarioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuario não encontrado."));
    }

    public List<Prontuario> listarTodos() {
        return prontuarioRepository.findAll();
    }

    public Prontuario atualizar(Integer id, Prontuario entity) {
        Prontuario prontuario = buscarPorId(id);
        validar(entity, id);
        prontuario.setStatus_prontuario(entity.getStatus_prontuario());
        prontuario.setObservacao_inicial(entity.getObservacao_inicial());
        prontuario.setPaciente(entity.getPaciente());
        return prontuarioRepository.save(prontuario);
    }

    public void excluir(Integer id) {
        Prontuario prontuario = buscarPorId(id);
        try {
            prontuarioRepository.delete(prontuario);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Prontuario: existem registros relacionados.", ex);
        }
    }

    private void validar(Prontuario entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prontuario não pode ser nulo.");
        if (entity.getPaciente() == null || entity.getPaciente().getId_paciente() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idPaciente é obrigatório.");
        entity.setPaciente(pacienteRepository.findById(entity.getPaciente().getId_paciente()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.")));
        if (entity.getStatus_prontuario() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status do prontuário é obrigatório.");
        if (prontuarioRepository.existeOutroProntuarioDoPaciente(entity.getPaciente().getId_paciente(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O paciente já possui prontuário.");
    }

}
