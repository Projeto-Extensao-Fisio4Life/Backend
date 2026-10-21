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

    @Transactional
    public Prontuario salvar(Prontuario entity) {
        validar(entity, null);

        return prontuarioRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Prontuario buscarPorId(Integer id) {
        return prontuarioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuario não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Prontuario> listarTodos() {
        return prontuarioRepository.findAll();
    }

    @Transactional
    public Prontuario atualizar(Integer id, Prontuario entity) {
        Prontuario existente = buscarPorId(id);
        validar(entity, id);
        existente.setStatus_prontuario(entity.getStatus_prontuario());
existente.setObservacao_inicial(entity.getObservacao_inicial());
existente.setPaciente(entity.getPaciente());
        return prontuarioRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Prontuario existente = buscarPorId(id);
        try {
            prontuarioRepository.delete(existente);
            prontuarioRepository.flush();
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

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
