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
        validar(entity, null);

        return fisioterapeutaRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Fisioterapeuta buscarPorId(Integer id) {
        return fisioterapeutaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Fisioterapeuta não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Fisioterapeuta> listarTodos() {
        return fisioterapeutaRepository.findAll();
    }

    @Transactional
    public Fisioterapeuta atualizar(Integer id, Fisioterapeuta entity) {
        Fisioterapeuta existente = buscarPorId(id);
        validar(entity, id);
        existente.setCrefito(entity.getCrefito());
existente.setEspecialidade(entity.getEspecialidade());
existente.setCnpj(entity.getCnpj());
existente.setUsuario(entity.getUsuario());
        return fisioterapeutaRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        Fisioterapeuta existente = buscarPorId(id);
        try {
            fisioterapeutaRepository.delete(existente);
            fisioterapeutaRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir Fisioterapeuta: existem registros relacionados.", ex);
        }
    }

    private void validar(Fisioterapeuta entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fisioterapeuta não pode ser nulo.");
        if (entity.getUsuario() == null || entity.getUsuario().getId_usuario() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idUsuario é obrigatório.");
        entity.setUsuario(usuarioRepository.findById(entity.getUsuario().getId_usuario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")));
        if (entity.getCrefito() == null || entity.getCrefito().isBlank() || entity.getCrefito().length() > 10)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CREFITO é obrigatório e deve ter até 10 caracteres.");
        if (entity.getCnpj() == null || !entity.getCnpj().matches("\\d{14}"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CNPJ deve conter exatamente 14 dígitos.");
        if (fisioterapeutaRepository.existeCrefitoEmOutro(entity.getCrefito(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CREFITO já cadastrado.");
        if (fisioterapeutaRepository.existeCnpjEmOutro(entity.getCnpj(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado para outro fisioterapeuta.");
        if (fisioterapeutaRepository.existeOutroFisioterapeutaParaUsuario(entity.getUsuario().getId_usuario(), idAtual))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O usuário já está vinculado a outro fisioterapeuta.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

}
