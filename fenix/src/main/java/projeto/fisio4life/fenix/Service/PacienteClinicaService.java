package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.PacienteClinica;
import projeto.fisio4life.fenix.Repository.ClinicaRepository;
import projeto.fisio4life.fenix.Repository.PacienteClinicaRepository;
import projeto.fisio4life.fenix.Repository.PacienteRepository;

@Service
public class PacienteClinicaService {

    private final PacienteClinicaRepository pacienteClinicaRepository;
    private final ClinicaRepository clinicaRepository;
    private final PacienteRepository pacienteRepository;

    public PacienteClinicaService(PacienteClinicaRepository pacienteClinicaRepository, ClinicaRepository clinicaRepository, PacienteRepository pacienteRepository) {
        this.pacienteClinicaRepository = pacienteClinicaRepository;
        this.clinicaRepository = clinicaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public PacienteClinica salvar(PacienteClinica entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "PacienteClinica não pode ser nulo."
            );
        }


        if (entity.getClinica() == null || entity.getClinica().getId_clinica() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idClinica é obrigatório."
            );
        }

        clinicaRepository.findById(entity.getClinica().getId_clinica())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Clinica não encontrado."
                ));


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

        if (entity.getId_vinculo() == null
                && pacienteClinicaRepository.existsByClinicaIdClinicaAndPacienteIdPaciente(
                entity.getClinica().getId_clinica(),
                entity.getPaciente().getId_paciente())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O paciente já está vinculado à clínica."
            );
        }

        return pacienteClinicaRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public PacienteClinica buscarPorId(Integer id) {
        return pacienteClinicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "PacienteClinica não encontrado."
                ));
    }

    @Transactional(readOnly = true)
    public List<PacienteClinica> listarTodos() {
        return pacienteClinicaRepository.findAll();
    }

    @Transactional
    public PacienteClinica atualizar(Integer id, PacienteClinica entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "PacienteClinica não pode ser nulo."
            );
        }

        PacienteClinica existente = buscarPorId(id);


        if (entity.getClinica() == null || entity.getClinica().getId_clinica() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idClinica é obrigatório."
            );
        }

        clinicaRepository.findById(entity.getClinica().getId_clinica())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Clinica não encontrado."
                ));


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

        if (entity.getId_vinculo() == null
                && pacienteClinicaRepository.existsByClinicaIdClinicaAndPacienteIdPaciente(
                entity.getClinica().getId_clinica(),
                entity.getPaciente().getId_paciente())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O paciente já está vinculado à clínica."
            );
        }

        existente.setData_cadastro(entity.getData_cadastro());
        existente.setUtlima_atualizacao(entity.getUtlima_atualizacao());
        existente.setStatus_paciente_clinica(entity.getStatus_paciente_clinica());
        existente.setClinica(entity.getClinica());
        existente.setPaciente(entity.getPaciente());

        return pacienteClinicaRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        PacienteClinica existente = buscarPorId(id);
        pacienteClinicaRepository.delete(existente);
    }
}