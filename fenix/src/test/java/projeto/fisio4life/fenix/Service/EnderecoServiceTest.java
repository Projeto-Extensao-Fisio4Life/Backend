
package projeto.fisio4life.fenix.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do EnderecoService")
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    private Endereco endereco;

    @BeforeEach
    void setUp() {

        // Arrange
        endereco = criarEndereco();

    }

    private Endereco criarEndereco() {

        Endereco novoEndereco = new Endereco();

        novoEndereco.setLogradouro("Rua das Flores");
        novoEndereco.setNumero(150);
        novoEndereco.setBairro("Centro");
        novoEndereco.setCidade("Osasco");
        novoEndereco.setEstado("SP");
        novoEndereco.setCep("06010-000");

        return novoEndereco;
    }



    @Test
    @DisplayName("Deve salvar um endereço com sucesso")
    void deveSalvarEnderecoComSucesso() {

        // Arrange
        when(enderecoRepository.save(endereco))
                .thenReturn(endereco);

        // Act
        Endereco resultado = enderecoService.salvar(endereco);

        // Assert
        assertNotNull(resultado);
        assertEquals("Rua das Flores", resultado.getLogradouro());
        assertEquals(150, resultado.getNumero());
        assertEquals("Centro", resultado.getBairro());

        verify(enderecoRepository, times(1)).save(endereco);

        System.out.println("\n========== TESTE SALVAR ENDEREÇO ==========");
        System.out.println("RESULTADO: ENDEREÇO SALVO COM SUCESSO");
        System.out.println("LOGRADOURO: " + resultado.getLogradouro());
        System.out.println("CIDADE: " + resultado.getCidade());
        System.out.println("CEP: " + resultado.getCep());
        System.out.println("===========================================");
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar salvar um endereço nulo")
    void deveLancarExcecaoQuandoEnderecoForNulo() {

        // Arrange
        Endereco enderecoNulo = null;

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.salvar(enderecoNulo)
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, excecao.getStatusCode());
        assertEquals("Endereco não pode ser nulo.", excecao.getReason());

        verifyNoInteractions(enderecoRepository);

        System.out.println("\n========== TESTE ENDEREÇO NULO ==========");
        System.out.println("RESULTADO: ENDEREÇO NULO IDENTIFICADO");
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("MENSAGEM: " + excecao.getReason());
        System.out.println("=========================================");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o logradouro for inválido")
    void deveLancarExcecaoQuandoLogradouroForInvalido() {

        // Arrange
        endereco.setLogradouro("");

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.salvar(endereco)
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, excecao.getStatusCode());
        assertEquals(
                "Logradouro é obrigatório e deve ter até 45 caracteres.",
                excecao.getReason()
        );

        verifyNoInteractions(enderecoRepository);

        System.out.println("\n========== TESTE LOGRADOURO INVÁLIDO ==========");
        System.out.println("RESULTADO: LOGRADOURO INVÁLIDO IDENTIFICADO");
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("===============================================");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o número for nulo")
    void deveLancarExcecaoQuandoNumeroForNulo() {

        // Arrange
        endereco.setNumero(null);

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.salvar(endereco)
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, excecao.getStatusCode());
        assertEquals("Número é obrigatório.", excecao.getReason());

        verifyNoInteractions(enderecoRepository);

        System.out.println("\n========== TESTE NÚMERO NULO ==========");
        System.out.println("RESULTADO: NÚMERO OBRIGATÓRIO IDENTIFICADO");
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("=======================================");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o bairro ultrapassar 45 caracteres")
    void deveLancarExcecaoQuandoBairroUltrapassarLimite() {

        // Arrange
        endereco.setBairro("A".repeat(46));

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.salvar(endereco)
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, excecao.getStatusCode());
        assertEquals(
                "Bairro é obrigatório e deve ter até 45 caracteres.",
                excecao.getReason()
        );

        verifyNoInteractions(enderecoRepository);

        System.out.println("\n========== TESTE LIMITE DE CARACTERES ==========");
        System.out.println("CAMPO: BAIRRO");
        System.out.println("CARACTERES INFORMADOS: 46");
        System.out.println("LIMITE PERMITIDO: 45");
        System.out.println("RESULTADO: VALIDAÇÃO REALIZADA");
        System.out.println("================================================");
    }



    @Test
    @DisplayName("Deve buscar um endereço por ID com sucesso")
    void deveBuscarEnderecoPorIdComSucesso() {

        // Arrange
        Integer id = 1;

        when(enderecoRepository.findById(id))
                .thenReturn(Optional.of(endereco));

        // Act
        Endereco resultado = enderecoService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals("Rua das Flores", resultado.getLogradouro());
        assertEquals("Osasco", resultado.getCidade());

        verify(enderecoRepository, times(1)).findById(id);

        System.out.println("\n========== TESTE BUSCAR ENDEREÇO ==========");
        System.out.println("RESULTADO: ENDEREÇO ENCONTRADO");
        System.out.println("ID CONSULTADO: " + id);
        System.out.println("LOGRADOURO: " + resultado.getLogradouro());
        System.out.println("===========================================");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o endereço não for encontrado")
    void deveLancarExcecaoQuandoEnderecoNaoForEncontrado() {

        // Arrange
        Integer id = 999;

        when(enderecoRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.buscarPorId(id)
        );

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, excecao.getStatusCode());
        assertEquals("Endereco não encontrado.", excecao.getReason());

        verify(enderecoRepository, times(1)).findById(id);

        System.out.println("\n========== TESTE ENDEREÇO NÃO ENCONTRADO ==========");
        System.out.println("ID CONSULTADO: " + id);
        System.out.println("RESULTADO: ENDEREÇO NÃO ENCONTRADO");
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("===================================================");
    }



    @Test
    @DisplayName("Deve listar todos os endereços")
    void deveListarTodosEnderecos() {

        // Arrange
        Endereco segundoEndereco = criarEndereco();
        segundoEndereco.setLogradouro("Avenida Paulista");

        List<Endereco> enderecos = List.of(
                endereco,
                segundoEndereco
        );

        when(enderecoRepository.findAll())
                .thenReturn(enderecos);

        // Act
        List<Endereco> resultado = enderecoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Rua das Flores", resultado.get(0).getLogradouro());
        assertEquals("Avenida Paulista", resultado.get(1).getLogradouro());

        verify(enderecoRepository, times(1)).findAll();

        System.out.println("\n========== TESTE LISTAR ENDEREÇOS ==========");
        System.out.println("RESULTADO: LISTAGEM REALIZADA COM SUCESSO");
        System.out.println("QUANTIDADE DE ENDEREÇOS: " + resultado.size());

        resultado.forEach(e ->
                System.out.println("LOGRADOURO: " + e.getLogradouro())
        );

        System.out.println("============================================");
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem endereços")
    void deveRetornarListaVaziaQuandoNaoExistiremEnderecos() {

        // Arrange
        when(enderecoRepository.findAll())
                .thenReturn(List.of());

        // Act
        List<Endereco> resultado = enderecoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(enderecoRepository, times(1)).findAll();

        System.out.println("\n========== TESTE LISTA VAZIA ==========");
        System.out.println("RESULTADO: NENHUM ENDEREÇO CADASTRADO");
        System.out.println("QUANTIDADE DE REGISTROS: " + resultado.size());
        System.out.println("=======================================");
    }



    @Test
    @DisplayName("Deve atualizar um endereço com sucesso")
    void deveAtualizarEnderecoComSucesso() {

        // Arrange
        Integer id = 1;

        Endereco novosDados = criarEndereco();

        novosDados.setLogradouro("Avenida Paulista");
        novosDados.setNumero(500);
        novosDados.setBairro("Bela Vista");
        novosDados.setCidade("São Paulo");
        novosDados.setEstado("SP");
        novosDados.setCep("01310-100");

        when(enderecoRepository.findById(id))
                .thenReturn(Optional.of(endereco));

        when(enderecoRepository.save(endereco))
                .thenReturn(endereco);

        // Act
        Endereco resultado = enderecoService.atualizar(id, novosDados);

        // Assert
        assertNotNull(resultado);
        assertEquals("Avenida Paulista", resultado.getLogradouro());
        assertEquals(500, resultado.getNumero());
        assertEquals("Bela Vista", resultado.getBairro());
        assertEquals("São Paulo", resultado.getCidade());
        assertEquals("SP", resultado.getEstado());
        assertEquals("01310-100", resultado.getCep());

        verify(enderecoRepository, times(1)).findById(id);
        verify(enderecoRepository, times(1)).save(endereco);

        System.out.println("\n========== TESTE ATUALIZAR ENDEREÇO ==========");
        System.out.println("RESULTADO: ENDEREÇO ATUALIZADO COM SUCESSO");
        System.out.println("NOVO LOGRADOURO: " + resultado.getLogradouro());
        System.out.println("NOVA CIDADE: " + resultado.getCidade());
        System.out.println("NOVO CEP: " + resultado.getCep());
        System.out.println("==============================================");
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar um endereço inexistente")
    void deveLancarExcecaoAoAtualizarEnderecoInexistente() {

        // Arrange
        Integer id = 999;

        when(enderecoRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.atualizar(id, endereco)
        );

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, excecao.getStatusCode());
        assertEquals("Endereco não encontrado.", excecao.getReason());

        verify(enderecoRepository, times(1)).findById(id);
        verify(enderecoRepository, never()).save(any(Endereco.class));

        System.out.println("\n========== TESTE ATUALIZAR INEXISTENTE ==========");
        System.out.println("RESULTADO: ENDEREÇO NÃO ENCONTRADO");
        System.out.println("ID CONSULTADO: " + id);
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("=================================================");
    }

    @Test
    @DisplayName("Deve impedir atualização quando os novos dados forem inválidos")
    void deveImpedirAtualizacaoComDadosInvalidos() {

        // Arrange
        Integer id = 1;

        endereco.setLogradouro("");

        when(enderecoRepository.findById(id))
                .thenReturn(Optional.of(criarEndereco()));

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.atualizar(id, endereco)
        );

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, excecao.getStatusCode());

        verify(enderecoRepository, times(1)).findById(id);
        verify(enderecoRepository, never()).save(any(Endereco.class));

        System.out.println("\n========== TESTE ATUALIZAÇÃO INVÁLIDA ==========");
        System.out.println("RESULTADO: ATUALIZAÇÃO IMPEDIDA");
        System.out.println("MOTIVO: LOGRADOURO INVÁLIDO");
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("================================================");
    }



    @Test
    @DisplayName("Deve excluir um endereço com sucesso")
    void deveExcluirEnderecoComSucesso() {

        // Arrange
        Integer id = 1;

        when(enderecoRepository.findById(id))
                .thenReturn(Optional.of(endereco));

        // Act
        assertDoesNotThrow(() -> enderecoService.excluir(id));

        // Assert
        verify(enderecoRepository, times(1)).findById(id);
        verify(enderecoRepository, times(1)).delete(endereco);
        verify(enderecoRepository, times(1)).flush();

        System.out.println("\n========== TESTE EXCLUIR ENDEREÇO ==========");
        System.out.println("RESULTADO: ENDEREÇO EXCLUÍDO COM SUCESSO");
        System.out.println("ID EXCLUÍDO: " + id);
        System.out.println("============================================");
    }

    @Test
    @DisplayName("Deve lançar exceção ao excluir um endereço inexistente")
    void deveLancarExcecaoAoExcluirEnderecoInexistente() {

        // Arrange
        Integer id = 999;

        when(enderecoRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.excluir(id)
        );

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, excecao.getStatusCode());
        assertEquals("Endereco não encontrado.", excecao.getReason());

        verify(enderecoRepository, times(1)).findById(id);
        verify(enderecoRepository, never()).delete(any(Endereco.class));
        verify(enderecoRepository, never()).flush();

        System.out.println("\n========== TESTE EXCLUIR INEXISTENTE ==========");
        System.out.println("RESULTADO: ENDEREÇO NÃO ENCONTRADO");
        System.out.println("ID CONSULTADO: " + id);
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("==============================================");
    }

    @Test
    @DisplayName("Deve lançar CONFLICT quando existirem registros relacionados")
    void deveLancarConflictQuandoExistiremRegistrosRelacionados() {

        // Arrange
        Integer id = 1;

        when(enderecoRepository.findById(id))
                .thenReturn(Optional.of(endereco));

        doThrow(new DataIntegrityViolationException(
                "Existem registros relacionados."
        )).when(enderecoRepository).flush();

        // Act
        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.excluir(id)
        );

        // Assert
        assertEquals(HttpStatus.CONFLICT, excecao.getStatusCode());

        assertEquals(
                "Não é possível excluir Endereco: existem registros relacionados.",
                excecao.getReason()
        );

        assertInstanceOf(
                DataIntegrityViolationException.class,
                excecao.getCause()
        );

        verify(enderecoRepository, times(1)).findById(id);
        verify(enderecoRepository, times(1)).delete(endereco);
        verify(enderecoRepository, times(1)).flush();

        System.out.println("\n========== TESTE EXCLUSÃO COM CONFLITO ==========");
        System.out.println("RESULTADO: EXCLUSÃO IMPEDIDA");
        System.out.println("MOTIVO: EXISTEM REGISTROS RELACIONADOS");
        System.out.println("STATUS HTTP: " + excecao.getStatusCode());
        System.out.println("===============================================");
    }
}

