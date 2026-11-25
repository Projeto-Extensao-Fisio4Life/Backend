package projeto.fisio4life.fenix.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;
import projeto.fisio4life.fenix.Repository.AgendamentoRepository;
import projeto.fisio4life.fenix.Repository.ClinicaRepository;
import projeto.fisio4life.fenix.Repository.FisioterapeutaRepository;
import projeto.fisio4life.fenix.Repository.PacienteRepository;

import java.util.List;

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
        validar(entity, null);

        return agendamentoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Agendamento buscarPorId(Integer id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    @Transactional
    public Agendamento atualizar(Integer id, Agendamento entity) {
        Agendamento existente = buscarPorId(id);
        validar(entity, id);
        existente.setData_agendamento(entity.getData_agendamento());
        existente.setHora_inicio(entity.getHora_inicio());
        existente.setHora_fim(entity.getHora_fim());
        existente.setStatus_agendamento(entity.getStatus_agendamento());
        existente.setObservacao(entity.getObservacao());
        existente.setClinica(entity.getClinica());
        existente.setFisioterapeuta(entity.getFisioterapeuta());
        return agendamentoRepository.save(existente);
    }

    @Transactional
    public void cancelar(Integer id) {
        Agendamento existente = buscarPorId(id);

        if (existente.getStatus_agendamento().equals(RegraStatusEnum.INATIVO)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento já está cancelado.");

        existente.setStatus_agendamento(RegraStatusEnum.INATIVO);
        agendamentoRepository.save(existente);
    }

//    @Transactional
//    public void excluir(Integer id) {
//        Agendamento existente = buscarPorId(id);
//        try {
//            agendamentoRepository.delete(existente);
//            agendamentoRepository.flush();
//        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,
//                    "Não é possível excluir Agendamento: existem registros relacionados.", ex);
//        }
//    }

    private void validar(Agendamento entity, Integer idAtual) {

        entity.setClinica(clinicaRepository.findById(entity.getClinica().getId_clinica())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica não encontrada.")));

        entity.setFisioterapeuta(fisioterapeutaRepository.findById(entity.getFisioterapeuta().getId_fisioterapeuta())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fisioterapeuta não encontrado.")));

        entity.setPaciente(pacienteRepository.findById(entity.getPaciente().getId_paciente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.")));

        if (!entity.getHora_fim().isAfter(entity.getHora_inicio()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A hora final deve ser posterior à inicial.");

        if (entity.getStatus_agendamento() != RegraStatusEnum.INATIVO &&
                agendamentoRepository.existeConflito(entity.getFisioterapeuta().getId_fisioterapeuta(), entity.getData_agendamento(), entity.getHora_inicio(), entity.getHora_fim(), RegraStatusEnum.INATIVO, idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Existe conflito de horário para este fisioterapeuta.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
