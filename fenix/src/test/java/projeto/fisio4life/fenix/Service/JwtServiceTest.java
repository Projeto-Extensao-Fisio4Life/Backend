
package projeto.fisio4life.fenix.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@DisplayName("Testes do JwtService")
class JwtServiceTest {

    private JwtService jwtService;

    private final String secret = "minha-chave-secreta-de-teste-com-mais-de-32-caracteres";

    private final long expiration = 3600000;

    private SecretKey key;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService(secret, expiration);

        key = io.jsonwebtoken.security.Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }


    @Test
    @DisplayName("Deve gerar token JWT para usuário comum")
    void deveGerarTokenParaUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setNome("Usuário Teste");
        usuario.setTipo_usuario(TipoUsuarioEnum.DONO_CLINICA);

        String token = jwtService.gerarToken(usuario);

        // Act
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        // Assert
        assertNotNull(token);
        assertEquals("Usuário Teste", claims.getSubject());
        assertEquals(1, claims.get("idUsuario", Integer.class));
        assertNotNull(claims.get("tipoUsuario"));
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
        assertTrue(claims.getExpiration().after(claims.getIssuedAt()));

        System.out.println("\n========== TESTE JWT USUÁRIO ==========");
        System.out.println("RESULTADO: TOKEN GERADO COM SUCESSO");
        System.out.println("SUBJECT: " + claims.getSubject());
        System.out.println("ID USUÁRIO: " + claims.get("idUsuario"));
        System.out.println("TIPO: " + claims.get("tipoUsuario"));
        System.out.println("EXPIRAÇÃO: " + claims.getExpiration());
        System.out.println("=======================================");
    }


    @Test
    @DisplayName("Deve gerar token JWT para fisioterapeuta")
    void deveGerarTokenParaFisioterapeuta() {

        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setNome("Fisioterapeuta Teste");
        usuario.setTipo_usuario(TipoUsuarioEnum.FISIOTERAPEUTA);

        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        fisioterapeuta.setId_fisioterapeuta(10);

        String token = jwtService.gerarToken(usuario, fisioterapeuta);

        // Act
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        // Assert
        assertNotNull(token);
        assertEquals("Fisioterapeuta Teste", claims.getSubject());
        assertEquals(1, claims.get("idUsuario", Integer.class));
        assertEquals(10, claims.get("fisioterapeutaId", Integer.class));
        assertNotNull(claims.get("tipoUsuario"));
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());

        System.out.println("\n========== TESTE JWT FISIOTERAPEUTA ==========");
        System.out.println("RESULTADO: TOKEN GERADO COM SUCESSO");
        System.out.println("SUBJECT: " + claims.getSubject());
        System.out.println("ID USUÁRIO: " + claims.get("idUsuario"));
        System.out.println("ID FISIOTERAPEUTA: " + claims.get("fisioterapeutaId"));
        System.out.println("TIPO: " + claims.get("tipoUsuario"));
        System.out.println("EXPIRAÇÃO: " + claims.getExpiration());
        System.out.println("==============================================");
    }


    @Test
    @DisplayName("Deve gerar token JWT para paciente")
    void deveGerarTokenParaPaciente() {

        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId_usuario(2);
        usuario.setNome("Paciente Teste");
        usuario.setTipo_usuario(TipoUsuarioEnum.PACIENTE);

        Paciente paciente = new Paciente();
        paciente.setId_paciente(20);

        String token = jwtService.gerarToken(usuario, paciente);

        // Act
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        // Assert
        assertNotNull(token);
        assertEquals("Paciente Teste", claims.getSubject());
        assertEquals(2, claims.get("idUsuario", Integer.class));
        assertEquals(20, claims.get("pacienteId", Integer.class));
        assertNotNull(claims.get("tipoUsuario"));
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());

        System.out.println("\n========== TESTE JWT PACIENTE ==========");
        System.out.println("RESULTADO: TOKEN GERADO COM SUCESSO");
        System.out.println("SUBJECT: " + claims.getSubject());
        System.out.println("ID USUÁRIO: " + claims.get("idUsuario"));
        System.out.println("ID PACIENTE: " + claims.get("pacienteId"));
        System.out.println("TIPO: " + claims.get("tipoUsuario"));
        System.out.println("EXPIRAÇÃO: " + claims.getExpiration());
        System.out.println("========================================");
    }
}
