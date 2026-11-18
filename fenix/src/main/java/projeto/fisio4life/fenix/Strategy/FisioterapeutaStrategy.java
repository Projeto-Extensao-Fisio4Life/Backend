package projeto.fisio4life.fenix.Strategy;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Dto.authDto.AuthRequestDto;
import projeto.fisio4life.fenix.Dto.authDto.AuthResponseDto;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;
import projeto.fisio4life.fenix.Interface.LoginStrategy;
import projeto.fisio4life.fenix.Mapper.AuthMapper;
import projeto.fisio4life.fenix.Repository.FisioterapeutaRepository;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;
import projeto.fisio4life.fenix.Service.JwtService;

@Component("FISIOTERAPEUTA")
public class FisioterapeutaStrategy implements LoginStrategy {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    public FisioterapeutaStrategy(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService, FisioterapeutaRepository fisioterapeutaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    @Override
    public AuthResponseDto login(AuthRequestDto dto) {
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

        Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findByUsuario(usuarioCadastrado);

        if (fisioterapeuta == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autorizado");
        }

        String token = jwtService.gerarToken(usuarioCadastrado, fisioterapeuta);
        return AuthMapper.toResponse(usuarioCadastrado, fisioterapeuta, token);


    }
}
