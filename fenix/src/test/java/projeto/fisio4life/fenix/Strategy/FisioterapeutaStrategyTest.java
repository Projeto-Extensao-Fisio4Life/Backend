
package projeto.fisio4life.fenix.Strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import projeto.fisio4life.fenix.Dto.authDto.AuthRequestDto;
import projeto.fisio4life.fenix.Dto.authDto.AuthResponseDto;
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Repository.FisioterapeutaRepository;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;
import projeto.fisio4life.fenix.Service.JwtService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FisioterapeutaStrategyTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private FisioterapeutaRepository fisioterapeutaRepository;

    @InjectMocks
    private FisioterapeutaStrategy fisioterapeutaStrategy;


    @Test
    @DisplayName("Deve realizar login de fisioterapeuta com sucesso")
    void deveRealizarLoginComSucesso() {

        // Arrange
        String email = "fisio@email.com";
        String senha = "123456";

        AuthRequestDto dto = new AuthRequestDto(email, senha);

        Endereco endereco = new Endereco();
        endereco.setId_endereco(1);

        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setEndereco(endereco);
        usuario.setNome("Fisioterapeuta Teste");
        usuario.setEmail(email);
        usuario.setSenha("senhaCriptografada");

        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();

        String tokenEsperado = "token.jwt.mock";

        when(usuarioRepository.findByEmail(email))
                .thenReturn(usuario);

        when(passwordEncoder.matches(senha, "senhaCriptografada"))
                .thenReturn(true);

        when(fisioterapeutaRepository.findByUsuario(usuario))
                .thenReturn(fisioterapeuta);

        when(jwtService.gerarToken(usuario, fisioterapeuta))
                .thenReturn(tokenEsperado);

        // Act
        AuthResponseDto resposta = fisioterapeutaStrategy.login(dto);

        // Assert
        assertNotNull(resposta);

        verify(usuarioRepository).findByEmail(email);
        verify(passwordEncoder).matches(senha, "senhaCriptografada");
        verify(fisioterapeutaRepository).findByUsuario(usuario);
        verify(jwtService).gerarToken(usuario, fisioterapeuta);

        System.out.println("\n========== TESTE DE LOGIN ==========");
        System.out.println("RESULTADO: LOGIN REALIZADO COM SUCESSO");
        System.out.println("EMAIL: " + email);
        System.out.println("TIPO: FISIOTERAPEUTA");
        System.out.println("TOKEN: " + tokenEsperado);
        System.out.println("====================================");
    }


    @Test
    @DisplayName("Deve lançar exceção quando usuário não existir")
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {

        // Arrange
        String email = "inexistente@email.com";
        String senha = "123456";

        AuthRequestDto dto = new AuthRequestDto(email, senha);

        when(usuarioRepository.findByEmail(email))
                .thenReturn(null);

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> fisioterapeutaStrategy.login(dto)
        );

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, excecao.getStatusCode());
        assertEquals("Usuário não autorizado", excecao.getReason());

        verify(usuarioRepository).findByEmail(email);

        verifyNoInteractions(
                passwordEncoder,
                fisioterapeutaRepository,
                jwtService
        );

        System.out.println("\n====== TESTE DE USUÁRIO INEXISTENTE ======");
        System.out.println("RESULTADO: LOGIN RECUSADO");
        System.out.println("EMAIL: " + email);
        System.out.println("STATUS: " + excecao.getStatusCode());
        System.out.println("MOTIVO: " + excecao.getReason());
        System.out.println("=========================================");
    }


    @Test
    @DisplayName("Deve lançar exceção quando a senha estiver incorreta")
    void deveLancarExcecaoQuandoSenhaEstiverIncorreta() {

        // Arrange
        String email = "fisio@email.com";
        String senha = "senhaErrada";

        AuthRequestDto dto = new AuthRequestDto(email, senha);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha("senhaCriptografada");

        when(usuarioRepository.findByEmail(email))
                .thenReturn(usuario);

        when(passwordEncoder.matches(senha, "senhaCriptografada"))
                .thenReturn(false);

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> fisioterapeutaStrategy.login(dto)
        );

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, excecao.getStatusCode());
        assertEquals("Usuário não autorizado", excecao.getReason());

        verify(usuarioRepository).findByEmail(email);

        verify(passwordEncoder).matches(
                senha,
                "senhaCriptografada"
        );

        verifyNoInteractions(
                fisioterapeutaRepository,
                jwtService
        );

        System.out.println("\n========== TESTE DE SENHA INCORRETA ==========");
        System.out.println("RESULTADO: LOGIN RECUSADO");
        System.out.println("EMAIL: " + email);
        System.out.println("STATUS: " + excecao.getStatusCode());
        System.out.println("MOTIVO: " + excecao.getReason());
        System.out.println("==============================================");
    }


    @Test
    @DisplayName("Deve lançar exceção quando usuário não possuir cadastro de fisioterapeuta")
    void deveLancarExcecaoQuandoFisioterapeutaNaoExistir() {

        // Arrange
        String email = "usuario@email.com";
        String senha = "123456";

        AuthRequestDto dto = new AuthRequestDto(email, senha);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha("senhaCriptografada");

        when(usuarioRepository.findByEmail(email))
                .thenReturn(usuario);

        when(passwordEncoder.matches(senha, "senhaCriptografada"))
                .thenReturn(true);

        when(fisioterapeutaRepository.findByUsuario(usuario))
                .thenReturn(null);

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> fisioterapeutaStrategy.login(dto)
        );

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, excecao.getStatusCode());
        assertEquals("Usuário não autorizado", excecao.getReason());

        verify(usuarioRepository).findByEmail(email);

        verify(passwordEncoder).matches(
                senha,
                "senhaCriptografada"
        );

        verify(fisioterapeutaRepository).findByUsuario(usuario);

        verifyNoInteractions(jwtService);

        System.out.println("\n====== TESTE DE FISIOTERAPEUTA INEXISTENTE ======");
        System.out.println("RESULTADO: LOGIN RECUSADO");
        System.out.println("EMAIL: " + email);
        System.out.println("STATUS: " + excecao.getStatusCode());
        System.out.println("MOTIVO: " + excecao.getReason());
        System.out.println("===============================================");
    }
}
