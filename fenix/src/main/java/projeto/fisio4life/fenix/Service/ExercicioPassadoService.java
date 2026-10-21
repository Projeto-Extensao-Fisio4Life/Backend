package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.ExercicioPassado;
import projeto.fisio4life.fenix.Repository.EvolucaoRepository;
import projeto.fisio4life.fenix.Repository.ExercicioPassadoRepository;
import projeto.fisio4life.fenix.Repository.ExercicioRepository;

@Service
public class ExercicioPassadoService {

    private final ExercicioPassadoRepository exercicioPassadoRepository;
private final EvolucaoRepository evolucaoRepository;
private final ExercicioRepository exercicioRepository;

    public ExercicioPassadoService(ExercicioPassadoRepository exercicioPassadoRepository, EvolucaoRepository evolucaoRepository, ExercicioRepository exercicioRepository) {
        this.exercicioPassadoRepository = exercicioPassadoRepository;
this.evolucaoRepository = evolucaoRepository;
this.exercicioRepository = exercicioRepository;
    }

    @Transactional
    public ExercicioPassado salvar(ExercicioPassado entity) {
        validar(entity, null);

        return exercicioPassadoRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public ExercicioPassado buscarPorId(Integer id) {
        return exercicioPassadoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "ExercicioPassado não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<ExercicioPassado> listarTodos() {
        return exercicioPassadoRepository.findAll();
    }

    @Transactional
    public ExercicioPassado atualizar(Integer id, ExercicioPassado entity) {
        ExercicioPassado existente = buscarPorId(id);
        validar(entity, id);
        existente.setSegmento(entity.getSegmento());
existente.setQuantidade_series(entity.getQuantidade_series());
existente.setQuantidade_repeticoes(entity.getQuantidade_repeticoes());
existente.setCarga(entity.getCarga());
existente.setTempo(entity.getTempo());
existente.setLocal(entity.getLocal());
existente.setExercicio(entity.getExercicio());
existente.setEvolucao(entity.getEvolucao());
        return exercicioPassadoRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        ExercicioPassado existente = buscarPorId(id);
        try {
            exercicioPassadoRepository.delete(existente);
            exercicioPassadoRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir ExercicioPassado: existem registros relacionados.", ex);
        }
    }

    private void validar(ExercicioPassado entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ExercicioPassado não pode ser nulo.");
        if (entity.getExercicio() == null || entity.getExercicio().getId_exercicio() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idExercicio é obrigatório.");
        if (entity.getEvolucao() == null || entity.getEvolucao().getId_evolucao() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idEvolucao é obrigatório.");
        entity.setExercicio(exercicioRepository.findById(entity.getExercicio().getId_exercicio()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercício não encontrado.")));
        entity.setEvolucao(evolucaoRepository.findById(entity.getEvolucao().getId_evolucao()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evolução não encontrada.")));
        validarTexto(entity.getSegmento(), "Segmento", 45);
        validarTexto(entity.getLocal(), "Local", 45);
        if (entity.getQuantidade_series() == null || entity.getQuantidade_series() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de séries não pode ser negativa.");
        if (entity.getQuantidade_repeticoes() == null || entity.getQuantidade_repeticoes() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de repetições não pode ser negativa.");
        if (entity.getCarga() == null || entity.getCarga() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carga não pode ser negativa.");
        if (entity.getTempo() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tempo é obrigatório.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

    @Transactional(readOnly = true)
    public List<ExercicioPassado> listarPorEvolucao(Integer idEvolucao) {
        if (!evolucaoRepository.existsById(idEvolucao))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evolução não encontrada.");
        return exercicioPassadoRepository.buscarPorEvolucao(idEvolucao);
    }

}
