package projeto.fisio4life.fenix.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TipoUsuarioNaoEncontrado extends RuntimeException {
    public TipoUsuarioNaoEncontrado(String message) {
        super(message);
    }
}
