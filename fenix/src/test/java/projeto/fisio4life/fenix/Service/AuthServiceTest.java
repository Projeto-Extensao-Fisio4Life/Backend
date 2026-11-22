
package projeto.fisio4life.fenix.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import projeto.fisio4life.fenix.Dto.authDto.AuthRequestDto;
import projeto.fisio4life.fenix.Dto.authDto.AuthResponseDto;
import projeto.fisio4life.fenix.Enum.StatusUsuarioEnum;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;
import projeto.fisio4life.fenix.Exception.TipoUsuarioNaoEncontrado;
import projeto.fisio4life.fenix.Interface.LoginStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do AuthService")
class AuthServiceTest {

    @Mock
    private LoginStrategy loginStrategy;

    private AuthService authService;

    private AuthRequestDto dto;

    private AuthResponseDto respostaEsperada;

    @BeforeEach
    void setUp() {

        // Arrange
        dto = new AuthRequestDto(
                "usuario@email.com",
                "123456"
        );

        respostaEsperada = new AuthResponseDto(
                1,
                10,
                "Usuário Teste",
                LocalDate.of(2000, 5, 15),
                "usuario@email.com",
                "11999999999",
                null,
                TipoUsuarioEnum.DONO_CLINICA,
                LocalDateTime.now(),
                StatusUsuarioEnum.ATIVO,
                "token.jwt.teste"
        );

        authService = new AuthService(
                Map.of("USUARIO", loginStrategy)
        );
    }

    @Test
    @DisplayName("Deve realizar login com sucesso")
    void deveRealizarLoginComSucesso() {

        // Arrange
        when(loginStrategy.login(dto))
                .thenReturn(respostaEsperada);

        // Act
        AuthResponseDto resposta = authService.login(dto, "USUARIO");

        // Assert
        assertNotNull(resposta);
        assertSame(respostaEsperada, resposta);

        assertEquals(1, resposta.getIdUsuario());
        assertEquals("Usuário Teste", resposta.getNome());
        assertEquals("usuario@email.com", resposta.getEmail());
        assertEquals("token.jwt.teste", resposta.getTokenJwt());

        verify(loginStrategy, times(1)).login(dto);

        System.out.println("\n========== TESTE LOGIN COM SUCESSO ==========");
        System.out.println("RESULTADO: LOGIN REALIZADO COM SUCESSO");
        System.out.println("USUÁRIO: " + resposta.getNome());
        System.out.println("EMAIL: " + resposta.getEmail());
        System.out.println("ID: " + resposta.getIdUsuario());
        System.out.println("TIPO: " + resposta.getTipoUsuario());
        System.out.println("=============================================");
    }

    @Test
    @DisplayName("Deve converter o tipo de usuário para maiúsculo")
    void deveConverterTipoUsuarioParaMaiusculo() {

        // Arrange
        when(loginStrategy.login(dto))
                .thenReturn(respostaEsperada);

        // Act
        AuthResponseDto resposta = authService.login(dto, "usuario");

        // Assert
        assertNotNull(resposta);
        assertSame(respostaEsperada, resposta);

        verify(loginStrategy, times(1)).login(dto);

        System.out.println("\n========== TESTE CONVERSÃO DE TIPO ==========");
        System.out.println("TIPO INFORMADO: usuario");
        System.out.println("TIPO IDENTIFICADO: USUARIO");
        System.out.println("RESULTADO: ESTRATÉGIA ENCONTRADA");
        System.out.println("=============================================");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o tipo de usuário não existir")
    void deveLancarExcecaoQuandoTipoUsuarioNaoExistir() {

        // Arrange
        String tipoInexistente = "ADMINISTRADOR";

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> authService.login(dto, tipoInexistente)
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, excecao.getStatusCode());

        assertInstanceOf(
                TipoUsuarioNaoEncontrado.class,
                excecao.getCause()
        );

        assertEquals(
                "Erro ao logar usuário",
                excecao.getReason()
        );

        verifyNoInteractions(loginStrategy);

        System.out.println("\n========== TESTE TIPO INEXISTENTE ==========");
        System.out.println("TIPO INFORMADO: " + tipoInexistente);
        System.out.println("RESULTADO: TIPO NÃO ENCONTRADO");
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("============================================");
    }

    @Test
    @DisplayName("Deve converter exceção da estratégia em BAD_REQUEST")
    void deveConverterExcecaoDaEstrategiaEmBadRequest() {

        // Arrange
        when(loginStrategy.login(dto))
                .thenThrow(new RuntimeException("Erro interno na estratégia"));

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> authService.login(dto, "USUARIO")
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, excecao.getStatusCode());

        assertEquals(
                "Erro ao logar usuário",
                excecao.getReason()
        );

        assertInstanceOf(
                RuntimeException.class,
                excecao.getCause()
        );

        assertEquals(
                "Erro interno na estratégia",
                excecao.getCause().getMessage()
        );

        verify(loginStrategy, times(1)).login(dto);

        System.out.println("\n========== TESTE ERRO NA ESTRATÉGIA ==========");
        System.out.println("RESULTADO: EXCEÇÃO CAPTURADA");
        System.out.println("ERRO ORIGINAL: " + excecao.getCause().getMessage());
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("MENSAGEM: " + excecao.getReason());
        System.out.println("==============================================");
    }
}
