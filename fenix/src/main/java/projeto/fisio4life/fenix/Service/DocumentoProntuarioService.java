package projeto.fisio4life.fenix.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.DocumentoProntuario;
import projeto.fisio4life.fenix.Repository.DocumentoProntuarioRepository;
import projeto.fisio4life.fenix.Repository.ProntuarioRepository;

@Service
public class DocumentoProntuarioService {

    private final DocumentoProntuarioRepository documentoProntuarioRepository;
private final ProntuarioRepository prontuarioRepository;

    public DocumentoProntuarioService(DocumentoProntuarioRepository documentoProntuarioRepository, ProntuarioRepository prontuarioRepository) {
        this.documentoProntuarioRepository = documentoProntuarioRepository;
this.prontuarioRepository = prontuarioRepository;
    }

    @Transactional
    public DocumentoProntuario salvar(DocumentoProntuario entity) {
        validar(entity, null);

        return documentoProntuarioRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public DocumentoProntuario buscarPorId(Integer id) {
        return documentoProntuarioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "DocumentoProntuario não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<DocumentoProntuario> listarTodos() {
        return documentoProntuarioRepository.findAll();
    }

    @Transactional
    public DocumentoProntuario atualizar(Integer id, DocumentoProntuario entity) {
        DocumentoProntuario existente = buscarPorId(id);
        validar(entity, id);
        existente.setNome_arquivo(entity.getNome_arquivo());
existente.setTipo_documento(entity.getTipo_documento());
existente.setS3_key(entity.getS3_key());
existente.setUrl_documento(entity.getUrl_documento());
existente.setData_upload(entity.getData_upload());
existente.setProntuario(entity.getProntuario());
        return documentoProntuarioRepository.save(existente);
    }

    @Transactional
    public void excluir(Integer id) {
        DocumentoProntuario existente = buscarPorId(id);
        try {
            documentoProntuarioRepository.delete(existente);
            documentoProntuarioRepository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível excluir DocumentoProntuario: existem registros relacionados.", ex);
        }
    }

    private void validar(DocumentoProntuario entity, Integer idAtual) {
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DocumentoProntuario não pode ser nulo.");
        if (entity.getProntuario() == null || entity.getProntuario().getId_prontuario() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idProntuario é obrigatório.");
        entity.setProntuario(prontuarioRepository.findById(entity.getProntuario().getId_prontuario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuário não encontrado.")));
        if (entity.getNome_arquivo() == null || entity.getNome_arquivo().isBlank() || entity.getNome_arquivo().length() > 45)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do arquivo é obrigatório e deve ter até 45 caracteres.");
        if (entity.getTipo_documento() == null || entity.getTipo_documento().isBlank() || entity.getTipo_documento().length() > 45)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo do documento é obrigatório e deve ter até 45 caracteres.");
        if (entity.getS3_key() == null || entity.getS3_key().isBlank() || entity.getS3_key().length() > 45)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "s3_key é obrigatório e deve ter até 45 caracteres.");
        if (entity.getUrl_documento() == null || entity.getUrl_documento().isBlank() || entity.getUrl_documento().length() > 45)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "URL do documento é obrigatória e deve ter até 45 caracteres.");
        if (entity.getData_upload() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de upload é obrigatória.");
    }

    private void validarTexto(String valor, String campo, int max) {
        if (valor == null || valor.isBlank() || valor.length() > max)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    campo + " é obrigatório e deve ter até " + max + " caracteres.");
    }

    @Transactional(readOnly = true)
    public List<DocumentoProntuario> listarPorProntuario(Integer idProntuario) {
        if (!prontuarioRepository.existsById(idProntuario))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuário não encontrado.");
        return documentoProntuarioRepository.buscarPorProntuario(idProntuario);
    }

}
