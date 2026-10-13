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
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Prontuario não pode ser nulo."
            );
        }


        if (entity.getPaciente() == null || entity.getPaciente().getId_paciente() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idPaciente é obrigatório."
            );
        }

        pacienteRepository.findById(entity.getPaciente().getId_paciente())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paciente não encontrado."
                ));

        if (entity.getId_prontuario() == null
                && pacienteRepository.existsById(
                entity.getPaciente().getId_paciente())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O paciente já possui prontuário."
            );
        }

        return prontuarioRepository.save(entity);
    }

    public Prontuario buscarPorId(Integer id) {
        return prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prontuario não encontrado."
                ));
    }

   public List<Prontuario> listarTodos() {
        return prontuarioRepository.findAll();
    }

    public Prontuario atualizar(Integer id, Prontuario entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Prontuario não pode ser nulo."
            );
        }

        Prontuario existente = buscarPorId(id);


        if (entity.getPaciente() == null || entity.getPaciente().getId_paciente() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idPaciente é obrigatório."
            );
        }

        pacienteRepository.findById(entity.getPaciente().getId_paciente())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paciente não encontrado."
                ));

        if (entity.getId_prontuario() == null
                && pacienteRepository.existsById(
                entity.getPaciente().getId_paciente())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O paciente já possui prontuário."
            );
        }

        existente.setData_abertura(entity.getData_abertura());
        existente.setStatus(entity.getStatus());
        existente.setObservacao_inicial(entity.getObservacao_inicial());
        existente.setPaciente(entity.getPaciente());

        return prontuarioRepository.save(existente);
    }

    public void excluir(Integer id) {
        Prontuario existente = buscarPorId(id);
        prontuarioRepository.delete(existente);
    }
}