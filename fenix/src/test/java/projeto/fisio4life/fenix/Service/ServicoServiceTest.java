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
import projeto.fisio4life.fenix.Entity.Servico;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;
import projeto.fisio4life.fenix.Repository.ServicoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ServicoServiceTest {

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private ServicoService servicoService;

    private Servico criarServicoTeste() {
        Servico servico = new Servico();
        servico.setId_servico(1);
        servico.setNome("Pacote 10 Sessões");
        servico.setQuantidade_sessoes(10);
        servico.setTipo("Pacote");
        servico.setStatus_servico(RegraStatusEnum.ATIVO);
        servico.setValor_sessao(new BigDecimal("250.00"));
        return servico;
    }

    @Nested
    @DisplayName("Testes do método salvar")
    class Salvar {

        @Test
        @DisplayName("Deve salvar serviço com sucesso")
        void deveSalvarServicoComSucesso() {
            // Given
            Servico servico = criarServicoTeste();
            Mockito.when(servicoRepository.existeNomeEmOutroServico("Pacote 10 Sessões", null)).thenReturn(false);
            Mockito.when(servicoRepository.save(servico)).thenReturn(servico);

            // When
            Servico resultado = servicoService.salvar(servico);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals("Pacote 10 Sessões", resultado.getNome());
            Assertions.assertEquals(new BigDecimal("250.00"), resultado.getValor_sessao());
            Mockito.verify(servicoRepository, Mockito.times(1)).save(servico);
        }

        @Test
        @DisplayName("Deve lançar CONFLICT quando já existir serviço com o mesmo nome")
        void deveLancarConflictQuandoNomeJaExistir() {
            // Given
            Servico servico = criarServicoTeste();
            Mockito.when(servicoRepository.existeNomeEmOutroServico("Pacote 10 Sessões", null)).thenReturn(true);

            // When & Then
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> servicoService.salvar(servico)
            );

            Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
            Assertions.assertEquals("Já existe um serviço cadastrado com este nome.", exception.getReason());
            Mockito.verify(servicoRepository, Mockito.never()).save(Mockito.any());
        }
    }

    @Nested
    @DisplayName("Testes do método buscarPorId")
    class BuscarPorId {

        @Test
        @DisplayName("Deve buscar serviço por ID com sucesso")
        void deveBuscarPorIdComSucesso() {
            // Given
            Servico servico = criarServicoTeste();
            Mockito.when(servicoRepository.findById(1)).thenReturn(Optional.of(servico));

            // When
            Servico resultado = servicoService.buscarPorId(1);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.getId_servico());
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND quando serviço não existir")
        void deveLancarExceptionQuandoServicoNaoEncontrado() {
            // Given
            Mockito.when(servicoRepository.findById(99)).thenReturn(Optional.empty());

            // When & Then
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> servicoService.buscarPorId(99)
            );

            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Serviço não encontrado.", exception.getReason());
        }
    }

    @Nested
    @DisplayName("Testes do método listarTodos")
    class ListarTodos {

        @Test
        @DisplayName("Deve retornar lista com 1 serviço")
        void deveListarTodosComSucesso() {
            // Given
            Mockito.when(servicoRepository.findAll()).thenReturn(List.of(criarServicoTeste()));

            // When
            List<Servico> resultado = servicoService.listarTodos();

            // Then
            Assertions.assertEquals(1, resultado.size());
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver serviços")
        void deveRetornarListaVazia() {
            // Given
            Mockito.when(servicoRepository.findAll()).thenReturn(List.of());

            // When
            List<Servico> resultado = servicoService.listarTodos();

            // Then
            Assertions.assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes do método atualizar")
    class Atualizar {

        @Test
        @DisplayName("Deve atualizar serviço com sucesso quando mantiver o próprio nome")
        void deveAtualizarServicoComSucesso() {
            // Given
            Integer id = 1;
            Servico existente = criarServicoTeste();

            Servico novosDados = new Servico();
            novosDados.setNome("Pacote 10 Sessões");
            novosDados.setQuantidade_sessoes(12);
            novosDados.setTipo("Pacote");
            novosDados.setStatus_servico(RegraStatusEnum.INATIVO);
            novosDados.setValor_sessao(new BigDecimal("280.00"));

            Mockito.when(servicoRepository.findById(id)).thenReturn(Optional.of(existente));
            Mockito.when(servicoRepository.existeNomeEmOutroServico("Pacote 10 Sessões",id)).thenReturn(false);
            Mockito.when(servicoRepository.save(existente)).thenReturn(existente);

            // When
            Servico resultado = servicoService.atualizar(id, novosDados);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertAll(
                    () -> Assertions.assertEquals("Pacote 10 Sessões", resultado.getNome()),
                    () -> Assertions.assertEquals(12, resultado.getQuantidade_sessoes()),
                    () -> Assertions.assertEquals("Pacote", resultado.getTipo()),
                    () -> Assertions.assertEquals(RegraStatusEnum.INATIVO, resultado.getStatus_servico()),
                    () -> Assertions.assertEquals(new BigDecimal("280.00"), resultado.getValor_sessao())
            );
            Mockito.verify(servicoRepository, Mockito.times(1)).save(existente);
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND ao tentar atualizar serviço inexistente")
        void deveLancarExceptionQuandoAtualizarIdInexistente() {
            // Given
            Servico novosDados = criarServicoTeste();
            Mockito.when(servicoRepository.findById(99)).thenReturn(Optional.empty());

            // When & Then
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> servicoService.atualizar(99, novosDados)
            );

            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        }

        @Test
        @DisplayName("Deve lançar CONFLICT quando já existir outro serviço com o mesmo nome ao atualizar")
        void deveLancarConflictQuandoNomeDuplicadoAoAtualizar() {
            // Given
            Integer id = 1;
            Servico existente = criarServicoTeste();

            Servico outroServico = new Servico();
            outroServico.setId_servico(2);
            outroServico.setNome("Sessão Avulsa");

            Servico novosDados = new Servico();
            novosDados.setNome("Sessão Avulsa");

            Mockito.when(servicoRepository.findById(id)).thenReturn(Optional.of(existente));
            Mockito.when(servicoRepository.existeNomeEmOutroServico("Sessão Avulsa",id)).thenReturn(true);

            // When & Then
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> servicoService.atualizar(id, novosDados)
            );

            Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
            Assertions.assertEquals("Já existe um serviço cadastrado com este nome.", exception.getReason());
            Mockito.verify(servicoRepository, Mockito.never()).save(Mockito.any());
        }
    }

    @Nested
    @DisplayName("Testes do método excluir (soft delete)")
    class Excluir {

        @Test
        @DisplayName("Deve inativar serviço com sucesso")
        void deveInativarServicoComSucesso() {
            // Given
            Integer id = 1;
            Servico existente = criarServicoTeste();
            Mockito.when(servicoRepository.findById(id)).thenReturn(Optional.of(existente));

            // When
            servicoService.excluir(id);

            // Then
            Assertions.assertEquals(RegraStatusEnum.INATIVO, existente.getStatus_servico());
            Mockito.verify(servicoRepository, Mockito.times(1)).save(existente);
            Mockito.verify(servicoRepository, Mockito.never()).delete(Mockito.any());
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND ao tentar inativar serviço inexistente")
        void deveLancarExceptionQuandoExcluirIdInexistente() {
            // Given

            Mockito.when(servicoRepository.findById(99)).thenReturn(Optional.empty());
// When & Then
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> servicoService.excluir(99)
            );
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Mockito.verify(servicoRepository, Mockito.never()).save(Mockito.any());
        }
    }
}



