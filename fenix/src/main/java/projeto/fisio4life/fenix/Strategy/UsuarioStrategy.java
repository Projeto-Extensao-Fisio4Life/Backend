package projeto.fisio4life.fenix.Strategy;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Dto.authDto.AuthRequestDto;
import projeto.fisio4life.fenix.Dto.authDto.AuthResponseDto;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Interface.LoginStrategy;
import projeto.fisio4life.fenix.Mapper.AuthMapper;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;
import projeto.fisio4life.fenix.Service.JwtService;


@Component("USUARIO")
public class UsuarioStrategy implements LoginStrategy {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioStrategy(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    @Override
    public AuthResponseDto login(AuthRequestDto dto) {
        String email = dto.getEmail();
        String senha = dto.getSenha();
        Usuario usuarioCadastrado = usuarioRepository.findByEmail(email);

        if (usuarioCadastrado == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autorizado");
        }
        boolean senhaCorreta = passwordEncoder.matches(senha, usuarioCadastrado.getSenha());

        if (!senhaCorreta) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autorizado");
        }

        String token = jwtService.gerarToken(usuarioCadastrado);
        return AuthMapper.toResponse(usuarioCadastrado, token);
    }
}
