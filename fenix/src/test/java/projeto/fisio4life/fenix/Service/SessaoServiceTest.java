package projeto.fisio4life.fenix.Service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Servico;
import projeto.fisio4life.fenix.Entity.Sessao;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;
import projeto.fisio4life.fenix.Enum.StatusSessaoEnum;
import projeto.fisio4life.fenix.Repository.AgendamentoRepository;
import projeto.fisio4life.fenix.Repository.ServicoRepository;
import projeto.fisio4life.fenix.Repository.SessaoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private SessaoService sessaoService;

    private Agendamento agendamentoPadrao;
    private Servico servicoPadrao;

    @BeforeEach
    void setUp() {
        Paciente paciente = new Paciente();
        paciente.setId_paciente(10);

        agendamentoPadrao = new Agendamento();
        agendamentoPadrao.setId_agendamento(1);
        agendamentoPadrao.setPaciente(paciente);
        agendamentoPadrao.setStatus_agendamento(RegraStatusEnum.ATIVO);
        agendamentoPadrao.setData_agendamento(LocalDate.of(2026, 11, 25));

        servicoPadrao = new Servico();
        servicoPadrao.setId_servico(1);
        servicoPadrao.setStatus_servico(RegraStatusEnum.ATIVO);
        servicoPadrao.setQuantidade_sessoes(10);

        Mockito.lenient().when(agendamentoRepository.findById(1)).thenReturn(Optional.of(agendamentoPadrao));
        Mockito.lenient().when(servicoRepository.findById(1)).thenReturn(Optional.of(servicoPadrao));
        Mockito.lenient().when(sessaoRepository.existeSessaoDoAgendamento(Mockito.eq(1), Mockito.any())).thenReturn(false);
        Mockito.lenient().when(sessaoRepository.contarSessoesDoPacienteNoServico(Mockito.eq(10), Mockito.eq(1), Mockito.any())).thenReturn(0);
    }

    private Sessao criarSessaoTeste() {
        Sessao sessao = new Sessao();
        sessao.setId_sessao(1);
        sessao.setAgendamento(agendamentoPadrao);
        sessao.setServico(servicoPadrao);
        sessao.setData_sessao(LocalDateTime.of(2026, 11, 25, 14, 0));
        sessao.setDescricao("Fisioterapia motora");
        sessao.setObservacao("Boa evolução");
        sessao.setStatus_sessao(StatusSessaoEnum.REALIZADA);
        sessao.setGera_cobranca(true);
        return sessao;
    }

    @Nested
    @DisplayName("Testes do método salvar e regras de negócio")
    class Salvar {

        @Test
        @DisplayName("Deve salvar sessão com sucesso")
        void deveSalvarSessaoComSucesso() {
            Sessao sessao = criarSessaoTeste();
            Mockito.when(sessaoRepository.save(sessao)).thenReturn(sessao);

            Sessao resultado = sessaoService.salvar(sessao);

            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(StatusSessaoEnum.REALIZADA, resultado.getStatus_sessao());
            Assertions.assertTrue(resultado.getGera_cobranca());
            Mockito.verify(sessaoRepository, Mockito.times(1)).save(sessao);
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND quando agendamento não existir")
        void deveLancarNotFoundQuandoAgendamentoNaoExistir() {
            Sessao sessao = criarSessaoTeste();
            Mockito.when(agendamentoRepository.findById(1)).thenReturn(Optional.empty());

            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.salvar(sessao));

            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
            Assertions.assertEquals("Agendamento não encontrado.", ex.getReason());
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND quando serviço não existir")
        void deveLancarNotFoundQuandoServicoNaoExistir() {
            Sessao sessao = criarSessaoTeste();
            Mockito.when(servicoRepository.findById(1)).thenReturn(Optional.empty());

            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.salvar(sessao));

            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
            Assertions.assertEquals("Serviço não encontrado.", ex.getReason());
        }

        @Test
        @DisplayName("Deve lançar BAD_REQUEST quando agendamento estiver cancelado")
        void deveLancarBadRequestQuandoAgendamentoCancelado() {
            Sessao sessao = criarSessaoTeste();
            agendamentoPadrao.setStatus_agendamento(RegraStatusEnum.INATIVO);

            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.salvar(sessao));

            Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            Assertions.assertEquals("Não é possível registrar sessão para agendamento cancelado.", ex.getReason());
        }

        @Test
        @DisplayName("Deve lançar CONFLICT quando serviço não estiver ativo")
        void deveLancarConflictQuandoServicoInativo() {
            Sessao sessao = criarSessaoTeste();
            servicoPadrao.setStatus_servico(RegraStatusEnum.INATIVO);

            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.salvar(sessao));

            Assertions.assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
            Assertions.assertEquals("O serviço não está ativo.", ex.getReason());
        }

        @Test
        @DisplayName("Deve lançar BAD_REQUEST quando data da sessão divergir da data do agendamento")
        void deveLancarBadRequestQuandoDataDiferenteDoAgendamento() {
            Sessao sessao = criarSessaoTeste();
            sessao.setData_sessao(LocalDateTime.of(2026, 11, 26, 14, 0));

            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.salvar(sessao));

            Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            Assertions.assertEquals("A data da sessão deve corresponder à data do agendamento.", ex.getReason());
        }

        @Test
        @DisplayName("Deve lançar CONFLICT quando agendamento já possuir sessão")
        void deveLancarConflictQuandoAgendamentoJaPossuiSessao() {
            Sessao sessao = criarSessaoTeste();
            Mockito.when(sessaoRepository.existeSessaoDoAgendamento(1, null)).thenReturn(true);

            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.salvar(sessao));

            Assertions.assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
            Assertions.assertEquals("Este agendamento já possui uma sessão.", ex.getReason());
        }

        @Test
        @DisplayName("Deve lançar CONFLICT quando paciente atingir limite de sessões contratadas")
        void deveLancarConflictQuandoAtingirLimiteSessoes() {
            Sessao sessao = criarSessaoTeste();
            Mockito.when(sessaoRepository.contarSessoesDoPacienteNoServico(10, 1, null)).thenReturn(10);

            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.salvar(sessao));

            Assertions.assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
            Assertions.assertEquals("O paciente já atingiu a quantidade de sessões contratada para este serviço.", ex.getReason());
        }
    }

    @Nested
    @DisplayName("Testes de consulta e listagem")
    class Consultas {

        @Test
        @DisplayName("Deve buscar sessão por ID com sucesso")
        void deveBuscarPorIdComSucesso() {
            Sessao sessao = criarSessaoTeste();
            Mockito.when(sessaoRepository.findById(1)).thenReturn(Optional.of(sessao));

            Sessao resultado = sessaoService.buscarPorId(1);

            Assertions.assertEquals(1, resultado.getId_sessao());
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND quando sessão não existir")
        void deveLancarNotFoundQuandoBuscarIdInexistente() {
            Mockito.when(sessaoRepository.findById(99)).thenReturn(Optional.empty());

            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.buscarPorId(99));

            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }

        @Test
        @DisplayName("Deve listar todas as sessões")
        void deveListarTodos() {
            Mockito.when(sessaoRepository.findAll()).thenReturn(List.of(criarSessaoTeste()));

            Assertions.assertEquals(1, sessaoService.listarTodos().size());
        }

        @Test
        @DisplayName("Deve listar sessões filtrando por serviço")
        void deveListarPorServicoComSucesso() {
            Sessao s1 = criarSessaoTeste();
            Servico outroServico = new Servico();
            outroServico.setId_servico(2);
            Sessao s2 = criarSessaoTeste();
            s2.setServico(outroServico);

            Mockito.when(servicoRepository.findById(1)).thenReturn(Optional.of(servicoPadrao));
            Mockito.when(sessaoRepository.findAll()).thenReturn(List.of(s1, s2));
            List resultado = sessaoService.listarPorServico(1);
            Assertions.assertEquals(1, resultado.size());
        }
        @Test
        @DisplayName("Deve lançar NOT_FOUND ao listar sessões de serviço inexistente")
        void deveLancarNotFoundAoListarPorServicoInexistente() {
            Mockito.when(servicoRepository.findById(99)).thenReturn(Optional.empty());
            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.listarPorServico(99));
            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }
    @Nested
    @DisplayName("Testes de atualização e exclusão")
    class AtualizarExcluir {
        @Test
        @DisplayName("Deve atualizar sessão com sucesso")
        void deveAtualizarSessaoComSucesso() {
            Sessao existente = criarSessaoTeste();
            Sessao novosDados = criarSessaoTeste();
            novosDados.setDescricao("Nova descrição");
            novosDados.setObservacao("Nova observação");
            novosDados.setStatus_sessao(StatusSessaoEnum.CANCELADA);
            novosDados.setGera_cobranca(false);
            Mockito.when(sessaoRepository.findById(1)).thenReturn(Optional.of(existente));
            Mockito.when(sessaoRepository.save(existente)).thenReturn(existente);
            Sessao resultado = sessaoService.atualizar(1, novosDados);
            Assertions.assertAll(
                    () -> Assertions.assertEquals("Nova descrição", resultado.getDescricao()),
                    () -> Assertions.assertEquals("Nova observação", resultado.getObservacao()),
                    () -> Assertions.assertEquals(StatusSessaoEnum.CANCELADA, resultado.getStatus_sessao()),
                    () -> Assertions.assertFalse(resultado.getGera_cobranca())
            );
        }
        @Test
        @DisplayName("Deve excluir sessão com sucesso")
        void deveExcluirSessaoComSucesso() {
            Sessao existente = criarSessaoTeste();
            Mockito.when(sessaoRepository.findById(1)).thenReturn(Optional.of(existente));
            Assertions.assertDoesNotThrow(() -> sessaoService.excluir(1));
            Mockito.verify(sessaoRepository, Mockito.times(1)).delete(existente);
        }
        @Test
        @DisplayName("Deve lançar CONFLICT ao excluir sessão com registros relacionados")
        void deveLancarConflictAoExcluirComVinculos() {
            Sessao existente = criarSessaoTeste();
            Mockito.when(sessaoRepository.findById(1)).thenReturn(Optional.of(existente));
            Mockito.doThrow(DataIntegrityViolationException.class).when(sessaoRepository).delete(existente);
            ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                    () -> sessaoService.excluir(1));
            Assertions.assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
        }
    }
}