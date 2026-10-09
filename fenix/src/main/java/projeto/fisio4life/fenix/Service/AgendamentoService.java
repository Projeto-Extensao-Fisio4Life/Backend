package projeto.fisio4life.fenix.Service;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Repository.AgendamentoRepository;
import projeto.fisio4life.fenix.Repository.ClinicaRepository;
import projeto.fisio4life.fenix.Repository.FisioterapeutaRepository;
import projeto.fisio4life.fenix.Repository.PacienteRepository;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClinicaRepository clinicaRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;
    private final PacienteRepository pacienteRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, ClinicaRepository clinicaRepository, FisioterapeutaRepository fisioterapeutaRepository, PacienteRepository pacienteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clinicaRepository = clinicaRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public Agendamento salvar(Agendamento entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Agendamento não pode ser nulo."
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


        if (entity.getFisioterapeuta() == null || entity.getFisioterapeuta().getId_fisioterapeuta() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idFisioterapeuta é obrigatório."
            );
        }

        fisioterapeutaRepository.findById(entity.getFisioterapeuta().getId_fisioterapeuta())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Fisioterapeuta não encontrado."
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

        if (entity.getHora_inicio() == null || entity.getHora_fim() == null
                || !entity.getHora_fim().isAfter(entity.getHora_inicio())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A hora de fim deve ser posterior à hora de início."
            );
        }

        // O SQL não informa a tabela de equivalência dos TINYINTs.
        // Ajuste o valor abaixo para o código oficial de "CANCELADO" do seu sistema.
        Integer statusCancelado = 2;

        if (agendamentoRepository.existeConflito(
                entity.getFisioterapeuta().getId_fisioterapeuta(),
                entity.getData_agendamento(),
                entity.getHora_inicio(),
                entity.getHora_fim(),
                statusCancelado,
                entity.getId_agendamento())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Existe conflito de horário para este fisioterapeuta."
            );
        }

        return agendamentoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Agendamento buscarPorId(Integer id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Agendamento não encontrado."
                ));
    }

    @Transactional(readOnly = true)
    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    @Transactional
    public Agendamento atualizar(Integer id, Agendamento entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Agendamento não pode ser nulo."
            );
        }

        Agendamento existente = buscarPorId(id);


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


        if (entity.getFisioterapeuta() == null || entity.getFisioterapeuta().getId_fisioterapeuta() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "idFisioterapeuta é obrigatório."
            );
        }

        fisioterapeutaRepository.findById(entity.getFisioterapeuta().getId_fisioterapeuta())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Fisioterapeuta não encontrado."
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

        if (entity.getHora_inicio() == null || entity.getHora_fim() == null
                || !entity.getHora_fim().isAfter(entity.getHora_inicio())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A hora de fim deve ser posterior à hora de início."
            );
        }

        // O SQL não informa a tabela de equivalência dos TINYINTs.
        // Ajuste o valor abaixo para o código oficial de "CANCELADO" do seu sistema.
        Integer statusCancelado = 2;

        if (agendamentoRepository.existeConflito(
                entity.getFisioterapeuta().getId_fisioterapeuta(),
                entity.getData_agendamento(),
                entity.getHora_inicio(),
                entity.getHora_fim(),
                statusCancelado,
                entity.getId_agendamento())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Existe conflito de horário para este fisioterapeuta."
            );
        }

        existente.setData_agendamento(entity.getData_agendamento());
        existente.setHora_inicio(entity.getHora_inicio());
        existente.setHora_fim(entity.getHora_fim());
        existente.setStatus(entity.getStatus());
        existente.setObservacao(entity.getObservacao());
        existente.setClinica(entity.getClinica());
        existente.setFisioterapeuta(entity.getFisioterapeuta());
        existente.setPaciente(entity.getPaciente());

        return agendamentoRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Agendamento existente = buscarPorId(id);
        agendamentoRepository.delete(existente);
    }
}