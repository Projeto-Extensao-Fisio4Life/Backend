package projeto.fisio4life.fenix.Service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Dto.authDto.AuthRequestDto;
import projeto.fisio4life.fenix.Dto.authDto.AuthResponseDto;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;
import projeto.fisio4life.fenix.Exception.TipoUsuarioNaoEncontrado;
import projeto.fisio4life.fenix.Interface.LoginStrategy;
import projeto.fisio4life.fenix.Mapper.AuthMapper;
import projeto.fisio4life.fenix.Repository.FisioterapeutaRepository;
import projeto.fisio4life.fenix.Repository.PacienteRepository;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;

import java.security.PrivilegedAction;
import java.util.Locale;
import java.util.Map;

@Service
public class AuthService {
    private final Map<String, LoginStrategy> strategies;

    public AuthService(Map<String, LoginStrategy> strategies) {
        this.strategies = strategies;
    }

    public AuthResponseDto login(AuthRequestDto dto, String tipo) {
        try {
            String chave = tipo.toUpperCase(Locale.ROOT);

            LoginStrategy strategy = strategies.get(chave);

            if (strategy == null) {
                throw new TipoUsuarioNaoEncontrado("Tipo de usuário não encontrado.");
            }

            return strategy.login(dto);
        } catch (Exception e) {
            System.out.println("Erro ao logar usuário: " + e);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Erro ao logar usuário",
                    e
            );
        }
    }
}


