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
import projeto.fisio4life.fenix.Mapper.AuthMapper;
import projeto.fisio4life.fenix.Repository.FisioterapeutaRepository;
import projeto.fisio4life.fenix.Repository.PacienteRepository;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;

import java.security.PrivilegedAction;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PacienteRepository pacienteRepository, FisioterapeutaRepository fisioterapeutaRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDto login(AuthRequestDto dto) {
        try {
            String email = dto.getEmail();
            String senha = dto.getSenha();
            Usuario usuarioCadastrado = usuarioRepository.findByEmail(email);
            System.out.println("Usuario encontrado: " + usuarioCadastrado.toString());

            if (usuarioCadastrado == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autorizado");
            }

            boolean senhaCorreta = passwordEncoder.matches(senha, usuarioCadastrado.getSenha());
            System.out.println("Senha correta: " + senhaCorreta);

            if (!senhaCorreta) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autorizado");
            }

            TipoUsuarioEnum tipoUsuario = usuarioCadastrado.getTipo_usuario();
            System.out.println("tipoUsuario: " + tipoUsuario);

            if (tipoUsuario == TipoUsuarioEnum.PACIENTE) {
                Paciente paciente = pacienteRepository.findByUsuario(usuarioCadastrado);
                if (paciente == null) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autorizado");
                }
                String token = jwtService.gerarToken(usuarioCadastrado, paciente);
                return AuthMapper.toResponse(usuarioCadastrado, paciente, token);
            } else if (tipoUsuario == TipoUsuarioEnum.FISIOTERAPEUTA) {
                Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findByUsuario(usuarioCadastrado);
                if (fisioterapeuta == null) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autorizado");
                }
                String token = jwtService.gerarToken(usuarioCadastrado, fisioterapeuta);
                return AuthMapper.toResponse(usuarioCadastrado, fisioterapeuta, token);
            }

            String token = jwtService.gerarToken(usuarioCadastrado);
            return AuthMapper.toResponse(usuarioCadastrado, token);
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


