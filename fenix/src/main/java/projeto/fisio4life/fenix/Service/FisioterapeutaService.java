package projeto.fisio4life.fenix.Service;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Repository.FisioterapeutaRepository;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;

@Service
public class FisioterapeutaService {

    private final FisioterapeutaRepository fisioterapeutaRepository;
    private final UsuarioRepository usuarioRepository;

    public FisioterapeutaService(FisioterapeutaRepository fisioterapeutaRepository, UsuarioRepository usuarioRepository) {
        this.fisioterapeutaRepository = fisioterapeutaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Fisioterapeuta salvar(Fisioterapeuta entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Fisioterapeuta não pode ser nulo."
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

        if (entity.getCrefito() != null && entity.getId_fisioterapeuta() == null
                && fisioterapeutaRepository.existsByCrefito(entity.getCrefito())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CREFITO já cadastrado.");
        }
        if (entity.getCnpj() != null && entity.getId_fisioterapeuta() == null
                && fisioterapeutaRepository.existsByCnpj(entity.getCnpj())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado.");
        }

        return fisioterapeutaRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Fisioterapeuta buscarPorId(Integer id) {
        return fisioterapeutaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Fisioterapeuta não encontrado."
                ));
    }

    @Transactional(readOnly = true)
    public List<Fisioterapeuta> listarTodos() {
        return fisioterapeutaRepository.findAll();
    }

    @Transactional
    public Fisioterapeuta atualizar(Integer id, Fisioterapeuta entity) {
        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Fisioterapeuta não pode ser nulo."
            );
        }

        Fisioterapeuta existente = buscarPorId(id);


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

        if (entity.getCrefito() != null && entity.getId_fisioterapeuta() == null
                && fisioterapeutaRepository.existsByCrefito(entity.getCrefito())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CREFITO já cadastrado.");
        }
        if (entity.getCnpj() != null && entity.getId_fisioterapeuta() == null
                && fisioterapeutaRepository.existsByCnpj(entity.getCnpj())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado.");
        }

        existente.setCrefito(entity.getCrefito());
        existente.setEspecialidade(entity.getEspecialidade());
        existente.setCnpj(entity.getCnpj());
        existente.setUsuario(entity.getUsuario());

        return fisioterapeutaRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Fisioterapeuta existente = buscarPorId(id);
        fisioterapeutaRepository.delete(existente);
    }
}