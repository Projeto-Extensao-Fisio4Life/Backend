package projeto.fisio4life.fenix.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Enum.StatusUsuarioEnum;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;
import projeto.fisio4life.fenix.Repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private PacienteService pacienteService;

    private Paciente criarPacienteTeste() {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setNome("João Pedro da Silva");
        usuario.setTipo_usuario(TipoUsuarioEnum.PACIENTE);
        usuario.setStatus_usuario(StatusUsuarioEnum.ATIVO);

        Paciente paciente = new Paciente();
        paciente.setId_paciente(1);
        paciente.setUsuario(usuario);
        paciente.setCpf("12345678901");
        paciente.setPermite_atendimento_grupo(true);
        return paciente;
    }

    @Nested
    @DisplayName("Testes do método salvar")
    class Salvar {

        @Test
        @DisplayName("Deve salvar usuário e paciente com sucesso")
        void deveSalvarPacienteComSucesso() {

            // Given
            Paciente paciente = criarPacienteTeste();
            Usuario usuario = paciente.getUsuario();

            Mockito.when(pacienteRepository.existeCpfEmOutroPaciente("12345678901", null))
                    .thenReturn(false);

            Mockito.when(usuarioService.salvar(usuario))
                    .thenReturn(usuario);

            Mockito.when(pacienteRepository.save(paciente))
                    .thenReturn(paciente);

            // When
            Paciente resultado = pacienteService.salvar(paciente);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.getId_paciente());
            Mockito.verify(usuarioService, Mockito.times(1)).salvar(usuario);
            Mockito.verify(pacienteRepository, Mockito.times(1)).save(paciente);
        }

        @Test
        @DisplayName("Deve lançar CONFLICT quando CPF já estiver cadastrado para outro paciente")
        void deveLancarExceptionQuandoCpfJaExistir() {

            // Given
            Paciente paciente = criarPacienteTeste();

            Mockito.when(pacienteRepository.existeCpfEmOutroPaciente("12345678901", null))
                    .thenReturn(true);

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> pacienteService.salvar(paciente)
            );

            // Then
            Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
            Assertions.assertEquals("CPF já cadastrado para outro paciente.", exception.getReason());
            Mockito.verify(usuarioService, Mockito.never()).salvar(Mockito.any());
            Mockito.verify(pacienteRepository, Mockito.never()).save(Mockito.any());
        }
    }

    @Nested
    @DisplayName("Testes do método buscarPorId")
    class BuscarPorId {

        @Test
        @DisplayName("Deve buscar paciente por ID com sucesso")
        void deveBuscarPorIdComSucesso() {

            // Given
            Paciente paciente = criarPacienteTeste();

            Mockito.when(pacienteRepository.findById(1))
                    .thenReturn(Optional.of(paciente));

            // When
            Paciente resultado = pacienteService.buscarPorId(1);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.getId_paciente());
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND quando paciente não existir")
        void deveLancarExceptionQuandoPacienteNaoEncontrado() {

            // Given
            Mockito.when(pacienteRepository.findById(99))
                    .thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> pacienteService.buscarPorId(99)
            );

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Paciente não encontrado.", exception.getReason());
        }
    }

    @Nested
    @DisplayName("Testes do método listarTodos")
    class ListarTodos {

        @Test
        @DisplayName("Deve retornar lista com pacientes")
        void deveListarTodosComSucesso() {

            // Given
            Mockito.when(pacienteRepository.findAll())
                    .thenReturn(List.of(criarPacienteTeste()));

            // When
            List<Paciente> resultado = pacienteService.listarTodos();

            // Then
            Assertions.assertEquals(1, resultado.size());
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver pacientes")
        void deveRetornarListaVazia() {

            // Given
            Mockito.when(pacienteRepository.findAll())
                    .thenReturn(List.of());

            // When
            List<Paciente> resultado = pacienteService.listarTodos();

            // Then
            Assertions.assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes do método atualizar")
    class Atualizar {

        @Test
        @DisplayName("Deve atualizar usuário vinculado e dados do paciente com sucesso")
        void deveAtualizarPacienteComSucesso() {

            // Given
            Integer id = 1;
            Paciente existente = criarPacienteTeste();

            Usuario novosDadosUsuario = new Usuario();
            novosDadosUsuario.setNome("João Pedro Atualizado");

            Paciente novosDados = new Paciente();
            novosDados.setCpf("98765432100");
            novosDados.setPermite_atendimento_grupo(false);
            novosDados.setUsuario(novosDadosUsuario);

            Mockito.when(pacienteRepository.findById(id))
                    .thenReturn(Optional.of(existente));

            Mockito.when(pacienteRepository.existeCpfEmOutroPaciente("98765432100", id))
                    .thenReturn(false);

            Mockito.when(pacienteRepository.save(existente))
                    .thenReturn(existente);

            // When
            Paciente resultado = pacienteService.atualizar(id, novosDados);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertAll(
                    () -> Assertions.assertEquals("98765432100", resultado.getCpf()),
                    () -> Assertions.assertFalse(resultado.getPermite_atendimento_grupo())
            );
            Mockito.verify(usuarioService, Mockito.times(1)).atualizar(1, novosDadosUsuario);
            Mockito.verify(pacienteRepository, Mockito.times(1)).save(existente);
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND ao tentar atualizar paciente inexistente")
        void deveLancarExceptionQuandoAtualizarIdInexistente() {

            // Given
            Paciente novosDados = criarPacienteTeste();

            Mockito.when(pacienteRepository.findById(99))
                    .thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> pacienteService.atualizar(99, novosDados)
            );

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Paciente não encontrado.", exception.getReason());
        }

        @Test
        @DisplayName("Deve lançar CONFLICT quando CPF já pertencer a outro paciente ao atualizar")
        void deveLancarConflictQuandoCpfDuplicadoAoAtualizar() {

            // Given
            Integer idAtual = 1;
            Paciente existente = criarPacienteTeste();
            Paciente novosDados = criarPacienteTeste();

            Mockito.when(pacienteRepository.findById(idAtual))
                    .thenReturn(Optional.of(existente));

            Mockito.when(pacienteRepository.existeCpfEmOutroPaciente("12345678901", idAtual))
                    .thenReturn(true);

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> pacienteService.atualizar(idAtual, novosDados)
            );

            // Then
            Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
            Assertions.assertEquals("CPF já cadastrado para outro paciente.", exception.getReason());
            Mockito.verify(usuarioService, Mockito.never()).atualizar(Mockito.any(), Mockito.any());
        }
    }

    @Nested
    @DisplayName("Testes do método excluir (soft delete no usuário)")
    class Excluir {

        @Test
        @DisplayName("Deve delegar exclusão (soft delete) para UsuarioService com sucesso")
        void deveExcluirPacienteComSucesso() {

            // Given
            Integer id = 1;
            Paciente existente = criarPacienteTeste();

            Mockito.when(pacienteRepository.findById(id))
                    .thenReturn(Optional.of(existente));

            // When
            pacienteService.excluir(id);

            // Then
            Mockito.verify(usuarioService, Mockito.times(1)).excluir(1);
            Mockito.verify(pacienteRepository, Mockito.never()).delete(Mockito.any());
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND ao tentar excluir paciente inexistente")
        void deveLancarExceptionQuandoExcluirIdInexistente() {

            // Given
            Mockito.when(pacienteRepository.findById(99))
                    .thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> pacienteService.excluir(99)
            );

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Paciente não encontrado.", exception.getReason());
            Mockito.verify(usuarioService, Mockito.never()).excluir(Mockito.any());
        }
    }



}