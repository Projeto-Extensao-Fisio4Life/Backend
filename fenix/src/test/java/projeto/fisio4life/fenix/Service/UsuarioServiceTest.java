package projeto.fisio4life.fenix.Service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Enum.StatusUsuarioEnum;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;
import projeto.fisio4life.fenix.Repository.EnderecoRepository;
import projeto.fisio4life.fenix.Repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {

        Endereco endereco = new Endereco();
        endereco.setId_endereco(1);

        Mockito.lenient().when(enderecoRepository.findById(1))
                .thenReturn(Optional.of(endereco));

        Mockito.lenient().when(passwordEncoder.encode(Mockito.anyString()))
                .thenReturn("senhaCriptografadaHash");
    }

    private Usuario criarUsuarioTeste() {

        Endereco endereco = new Endereco();
        endereco.setId_endereco(1);

        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setNome("Maria Silva");
        usuario.setData_nascimento(LocalDate.of(1995, 5, 15));
        usuario.setEmail("maria@email.com");
        usuario.setCelular("11987654321");
        usuario.setTelefone("1133334444");
        usuario.setSenha("senhaPura123");
        usuario.setTipo_usuario(TipoUsuarioEnum.PACIENTE);
        usuario.setStatus_usuario(StatusUsuarioEnum.ATIVO);
        usuario.setEndereco(endereco);

        return usuario;
    }

    @Nested
    @DisplayName("Testes do método salvar")
    class Salvar {

        @Test
        @DisplayName("Deve salvar usuário com senha criptografada e data de cadastro")
        void deveSalvarUsuarioComSucesso() {

            // Given
            Usuario usuario = criarUsuarioTeste();
            Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

            // When
            Usuario resultado = usuarioService.salvar(usuario);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals("senhaCriptografadaHash", resultado.getSenha());
            Assertions.assertNotNull(resultado.getData_cadastro());
            Mockito.verify(passwordEncoder, Mockito.times(1)).encode("senhaPura123");
            Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuario);
        }

        @Test
        @DisplayName("Deve validar quando endereço não existir")
        void deveLancarExceptionQuandoEnderecoNaoEncontrado() {

            // Given
            Usuario usuario = criarUsuarioTeste();
            Mockito.when(enderecoRepository.findById(1)).thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> usuarioService.salvar(usuario)
            );

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Endereço não encontrado.", exception.getReason());
        }
    }

    @Nested
    @DisplayName("Testes do método buscarPorId")
    class BuscarPorId {

        @Test
        @DisplayName("Deve buscar usuário por ID com sucesso")
        void deveBuscarPorIdComSucesso() {

            // Given
            Usuario usuario = criarUsuarioTeste();
            Mockito.when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

            // When
            Usuario resultado = usuarioService.buscarPorId(1);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.getId_usuario());
        }

        @Test
        @DisplayName("Deve validar quando usuário não existir")
        void deveLancarExceptionQuandoUsuarioNaoEncontrado() {

            // Given
            Mockito.when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> usuarioService.buscarPorId(99)
            );

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Usuario não encontrado.", exception.getReason());
        }
    }

    @Nested
    @DisplayName("Testes do método listarTodos")
    class ListarTodos {

        @Test
        @DisplayName("Deve retornar lista com 1 usuário")
        void deveListarTodosComSucesso() {

            // Given
            Mockito.when(usuarioRepository.findAll()).thenReturn(List.of(criarUsuarioTeste()));

            // When
            List<Usuario> resultado = usuarioService.listarTodos();

            // Then
            Assertions.assertEquals(1, resultado.size());
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver usuários")
        void deveRetornarListaVazia() {

            // Given
            Mockito.when(usuarioRepository.findAll()).thenReturn(List.of());

            // When
            List<Usuario> resultado = usuarioService.listarTodos();

            // Then
            Assertions.assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes do método atualizar")
    class Atualizar {

        @Test
        @DisplayName("Deve atualizar todos os campos do usuário com sucesso")
        void deveAtualizarUsuarioComSucesso() {

            // Given
            Integer id = 1;
            Usuario existente = criarUsuarioTeste();

            Endereco novoEndereco = new Endereco();
            novoEndereco.setId_endereco(2);

            Usuario novosDados = new Usuario();
            novosDados.setNome("Maria Oliveira");
            novosDados.setData_nascimento(LocalDate.of(1998, 8, 20));
            novosDados.setEmail("maria.nova@email.com");
            novosDados.setCelular("11999998888");
            novosDados.setTelefone("1144445555");
            novosDados.setSenha("novaSenha456");
            novosDados.setTipo_usuario(TipoUsuarioEnum.FISIOTERAPEUTA);
            novosDados.setStatus_usuario(StatusUsuarioEnum.INATIVO);
            novosDados.setEndereco(novoEndereco);

            Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(existente));
            Mockito.when(enderecoRepository.findById(2)).thenReturn(Optional.of(novoEndereco));
            Mockito.when(passwordEncoder.encode("novaSenha456")).thenReturn("novaSenhaHash456");
            Mockito.when(usuarioRepository.save(existente)).thenReturn(existente);

            // When
            Usuario resultado = usuarioService.atualizar(id, novosDados);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertAll(
                    () -> Assertions.assertEquals("Maria Oliveira", resultado.getNome()),
                    () -> Assertions.assertEquals(LocalDate.of(1998, 8, 20), resultado.getData_nascimento()),
                    () -> Assertions.assertEquals("maria.nova@email.com", resultado.getEmail()),
                    () -> Assertions.assertEquals("11999998888", resultado.getCelular()),
                    () -> Assertions.assertEquals("1144445555", resultado.getTelefone()),
                    () -> Assertions.assertEquals("novaSenhaHash456", resultado.getSenha()),
                    () -> Assertions.assertEquals(TipoUsuarioEnum.FISIOTERAPEUTA, resultado.getTipo_usuario()),
                    () -> Assertions.assertEquals(StatusUsuarioEnum.INATIVO, resultado.getStatus_usuario()),
                    () -> Assertions.assertEquals(2, resultado.getEndereco().getId_endereco())
            );
        }

        @Test
        @DisplayName("Deve validar quando usuário inexistente")
        void deveLancarExceptionQuandoAtualizarIdInexistente() {
            // Given
            Usuario novosDados = criarUsuarioTeste();
            Mockito.when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> usuarioService.atualizar(99, novosDados)
            );

            //Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        }
    }

    @Nested
    @DisplayName("Testes do método excluir (soft delete)")
    class Excluir {

        @Test
        @DisplayName("Deve inativar usuário com sucesso")
        void deveInativarUsuarioComSucesso() {

            // Given: usuário começa ATIVO
            Integer id = 1;
            Usuario existente = criarUsuarioTeste();
            Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(existente));

            // When
            usuarioService.excluir(id);

            // Then:
            Assertions.assertEquals(StatusUsuarioEnum.INATIVO, existente.getStatus_usuario());
            Mockito.verify(usuarioRepository, Mockito.times(1)).save(existente);
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND ao tentar inativar usuário inexistente")
        void deveLancarExceptionQuandoExcluirIdInexistente() {

            // Given
            Integer idInexistente = 99;
            Mockito.when(usuarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> usuarioService.excluir(idInexistente)
            );

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Usuario não encontrado.", exception.getReason());
        }
    }
}