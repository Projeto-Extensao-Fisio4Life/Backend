package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Exercicio;
import projeto.fisio4life.fenix.Repository.ExercicioRepository;

@Service
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    public Exercicio salvar(Exercicio entity) {
        validar(entity, null);

        return exercicioRepository.save(entity);
    }

    public Exercicio buscarPorId(Integer id) {
        return exercicioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercicio não encontrado."));
    }

    public List<Exercicio> listarTodos() {
        return exercicioRepository.findAll();
    }

    public Exercicio atualizar(Integer id, Exercicio entity) {
        Exercicio exercicio = buscarPorId(id);
        validar(entity, id);
        exercicio.setCategoria(entity.getCategoria());
        exercicio.setNome(entity.getNome());
        return exercicioRepository.save(exercicio);
    }

    public void excluir(Integer id) {
        Exercicio exercicio = buscarPorId(id);
        try {
            exercicioRepository.delete(exercicio);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Exercicio: existem registros relacionados.", ex);
        }
    }

    private void validar(Exercicio entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exercicio não pode ser nulo.");
        validarTexto(entity.getCategoria(), "Categoria", 45);
        validarTexto(entity.getNome(), "Nome", 45);
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
