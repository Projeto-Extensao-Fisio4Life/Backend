package projeto.fisio4life.fenix.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {


    private static String secret;
    private static long expiration;

    public JwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration
    ) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String gerarToken(Usuario usuario, Fisioterapeuta fisioterapeuta) {
        SecretKey key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + expiration);
        return Jwts.builder()
                .subject(usuario.getNome())
                .claim("idUsuario", usuario.getId_usuario())
                .claim("tipoUsuario", usuario.getTipo_usuario())
                .claim("fisioterapeutaId", fisioterapeuta.getId_fisioterapeuta())
                .issuedAt(agora)
                .expiration(expiracao)
                .signWith(key)
                .compact();
    }
    public String gerarToken(Usuario usuario) {
        SecretKey key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + expiration);
        return Jwts.builder()
                .subject(usuario.getNome())
                .claim("idUsuario", usuario.getId_usuario())
                .claim("tipoUsuario", usuario.getTipo_usuario())
                .issuedAt(agora)
                .expiration(expiracao)
                .signWith(key)
                .compact();
    }

    public String gerarToken(Usuario usuario, Paciente paciente) {
        SecretKey key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + expiration);
        return Jwts.builder()
                .subject(usuario.getNome())
                .claim("idUsuario", usuario.getId_usuario())
                .claim("tipoUsuario", usuario.getTipo_usuario())
                .claim("pacienteId", paciente.getId_paciente())
                .issuedAt(agora)
                .expiration(expiracao)
                .signWith(key)
                .compact();
    }
}
