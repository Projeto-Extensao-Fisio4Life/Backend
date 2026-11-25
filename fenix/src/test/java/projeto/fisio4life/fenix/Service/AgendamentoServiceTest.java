package projeto.fisio4life.fenix.Service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;
import projeto.fisio4life.fenix.Repository.AgendamentoRepository;
import projeto.fisio4life.fenix.Repository.ClinicaRepository;
import projeto.fisio4life.fenix.Repository.FisioterapeutaRepository;
import projeto.fisio4life.fenix.Repository.PacienteRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private ClinicaRepository clinicaRepository;

    @Mock
    private FisioterapeutaRepository fisioterapeutaRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private AgendamentoService agendamentoService;

    // Para não precisar ensinar os findById a não retornar Optional.empty()
    @BeforeEach
    void setUp() {

        Clinica clinica = new Clinica();
        clinica.setId_clinica(1);

        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        fisioterapeuta.setId_fisioterapeuta(1);

        Paciente paciente = new Paciente();
        paciente.setId_paciente(1);

        Mockito.lenient().when(clinicaRepository.findById(1))
                .thenReturn(Optional.of(clinica));

        Mockito.lenient().when(fisioterapeutaRepository.findById(1))
                .thenReturn(Optional.of(fisioterapeuta));

        Mockito.lenient().when(pacienteRepository.findById(1))
                .thenReturn(Optional.of(paciente));
    }

    // Criar agendamentoTeste
    public Agendamento criarAgendamentoTeste() {

        Clinica clinica = new Clinica();
        clinica.setId_clinica(1);

        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        fisioterapeuta.setId_fisioterapeuta(1);

        Paciente paciente = new Paciente();
        paciente.setId_paciente(1);

        Agendamento agendamento = new Agendamento();
        agendamento.setId_agendamento(1);
        agendamento.setClinica(clinica);
        agendamento.setFisioterapeuta(fisioterapeuta);
        agendamento.setPaciente(paciente);
        agendamento.setData_agendamento(LocalDate.of(2026, 11, 23));
        agendamento.setHora_inicio(LocalTime.of(10, 0));
        agendamento.setHora_fim(LocalTime.of(11, 0));
        agendamento.setStatus_agendamento(RegraStatusEnum.ATIVO);

        return agendamento;
    }

    @Nested
    @DisplayName("Testes do método validar")
    class Validar {

        @Test
        @DisplayName("Deve validar clinica não encontrada")
        void deveLancarExceptionQuandoClinicaNaoEncontrada() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();
            Integer IdClinica = agendamento.getClinica().getId_clinica();

            Mockito.when(clinicaRepository.findById(IdClinica))
                    .thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                    () -> agendamentoService.salvar(agendamento));

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Clínica não encontrada.", exception.getReason());
        }

        @Test
        @DisplayName("Deve validar fisioterapeuta não encontrado")
        void deveLancarExceptionQuandoFisioterapeutaNaoEncontrado() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();
            Integer IdFisioterapeuta = agendamento.getFisioterapeuta().getId_fisioterapeuta();

            Mockito.when(fisioterapeutaRepository.findById(IdFisioterapeuta))
                    .thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                    () -> agendamentoService.salvar(agendamento));

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Fisioterapeuta não encontrado.", exception.getReason());
        }

        @Test
        @DisplayName("Deve validar paciente não encontrado")
        void deveLancarExceptionQuandoPacienteNaoEncontrado() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();
            Integer IdPaciente = agendamento.getPaciente().getId_paciente();

            Mockito.when(pacienteRepository.findById(IdPaciente))
                    .thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                    () -> agendamentoService.salvar(agendamento));

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Paciente não encontrado.", exception.getReason());
        }

        @Test
        @DisplayName("Deve validar hora_fim antes da hora_inicio")
        void deveLancarExceptionQuandoHoraFimAntesDaHoraInicio() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();
            agendamento.setHora_inicio(LocalTime.of(10,0));
            agendamento.setHora_fim(LocalTime.of(9,0));

            // When
            ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                    () -> agendamentoService.salvar(agendamento));

            // Then
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
            Assertions.assertEquals("A hora final deve ser posterior à inicial.", exception.getReason());
        }

        @Test
        @DisplayName("Deve validar conflito de horário para o fisioterapeuta")
        void deveLancarExceptionQuandoTemConflitoDeHorario() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();
            Integer id_fisioterapeuta = 1;
            LocalDate data_agendamento = LocalDate.of(2026, 11, 23);
            LocalTime hora_incio = LocalTime.of(10, 0);
            LocalTime hora_fim = LocalTime.of(11, 0);
            RegraStatusEnum status_agendamento = (RegraStatusEnum.INATIVO);
            Integer idAtual = null;

            Mockito.when(agendamentoRepository.existeConflito(id_fisioterapeuta, data_agendamento, hora_incio, hora_fim, status_agendamento, idAtual))
                    .thenReturn(true);

            // When
            ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                    () -> agendamentoService.salvar(agendamento));

            // Then
            Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
            Assertions.assertEquals("Existe conflito de horário para este fisioterapeuta.", exception.getReason());
        }
    }

    @Nested
    @DisplayName("Testes do método salvar")
    class Salvar{

        @Test
        @DisplayName("Deve salvar o agendamento corretamente")
        void deveSalvarOAgendamentoCorretamente() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();

            Mockito.when(agendamentoRepository.save(agendamento))
                    .thenReturn(agendamento);

            // When
            Agendamento resultado = agendamentoService.salvar(agendamento);

            // Then
            Assertions.assertEquals(1, resultado.getId_agendamento());
            Assertions.assertEquals(agendamento.getClinica(), resultado.getClinica());
            Assertions.assertEquals(agendamento.getFisioterapeuta(), resultado.getFisioterapeuta());
            Assertions.assertEquals(agendamento.getPaciente(), resultado.getPaciente());
            Assertions.assertEquals(LocalDate.of(2026, 11, 23), resultado.getData_agendamento());
            Assertions.assertEquals(LocalTime.of(10, 0), resultado.getHora_inicio());
            Assertions.assertEquals(LocalTime.of(11, 0), resultado.getHora_fim());
            Assertions.assertEquals((byte) 1, resultado.getStatus_agendamento());

        }
    }

    @Nested
    @DisplayName("Testes do método buscarPorId")
    class BuscarPorId {

        @Test
        @DisplayName("Deve buscar agendamento por id")
        void deveBuscarAgendamentoPorId() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();
            Integer id = 1;
            Optional<Agendamento> agendamentoOptional = Optional.of(agendamento);

            Mockito.when(agendamentoRepository.findById(id))
                    .thenReturn(agendamentoOptional);

            // When
            Agendamento resultado = agendamentoService.buscarPorId(id);

            // Then
            Assertions.assertEquals(1, resultado.getId_agendamento());
            Assertions.assertEquals(agendamento.getClinica(), resultado.getClinica());
            Assertions.assertEquals(agendamento.getFisioterapeuta(), resultado.getFisioterapeuta());
            Assertions.assertEquals(agendamento.getPaciente(), resultado.getPaciente());
            Assertions.assertEquals(LocalDate.of(2026, 11, 23), resultado.getData_agendamento());
            Assertions.assertEquals(LocalTime.of(10, 0), resultado.getHora_inicio());
            Assertions.assertEquals(LocalTime.of(11, 0), resultado.getHora_fim());
            Assertions.assertEquals((byte) 1, resultado.getStatus_agendamento());
        }

        @Test
        @DisplayName("Deve validar agendamento")
        void deveLancarExceptionQuandoAgendamentoNaoEncontrado() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();
            Integer id = 1;

            Mockito.when(agendamentoRepository.findById(id))
                    .thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                    () -> agendamentoService.buscarPorId(id));

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Agendamento não encontrado.", exception.getReason());

        }
    }

    @Nested
    @DisplayName("Testes do método listarTodos")
    class ListarTodos {

        @Test
        @DisplayName("Deve listar todos os agendamentos")
        void deveListarTodosOsAgendamentos() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();
            List<Agendamento> agendamentos = List.of(agendamento);

            Mockito.when(agendamentoRepository.findAll())
                    .thenReturn(agendamentos);

            // When
            List<Agendamento> resultado = agendamentoService.listarTodos();

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.size());
            Assertions.assertEquals(1, resultado.get(0).getId_agendamento());

        }

        @Test
        @DisplayName("Deve retornar lista vazia")
        void deveRetornarListaVazia() {

            // Given
            List<Agendamento> listaVazia = List.of();

            Mockito.when(agendamentoRepository.findAll())
                    .thenReturn(listaVazia);

            // When
            List<Agendamento> resultado = agendamentoService.listarTodos();

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertTrue(resultado.isEmpty());

        }
    }

    @Nested
    @DisplayName("Testes do método atualizar")
    class Atualizar {

        @Test
        @DisplayName("Deve atualizar todos os campos permitidos do agendamento com sucesso")
        void deveAtualizarAgendamentoComSucesso() {

            // Given
            Integer id = 1;
            Agendamento existente = criarAgendamentoTeste();

            Clinica novaClinica = new Clinica();
            novaClinica.setId_clinica(2);

            Fisioterapeuta novoFisio = new Fisioterapeuta();
            novoFisio.setId_fisioterapeuta(2);

            Agendamento entity = criarAgendamentoTeste();
            entity.setClinica(novaClinica);
            entity.setFisioterapeuta(novoFisio);
            entity.setData_agendamento(LocalDate.of(2026, 12, 10));
            entity.setHora_inicio(LocalTime.of(14, 0));
            entity.setHora_fim(LocalTime.of(15, 0));
            entity.setStatus_agendamento(RegraStatusEnum.INATIVO);
            entity.setObservacao("Observação atualizada");

            Mockito.when(agendamentoRepository.findById(id)).thenReturn(Optional.of(existente));
            Mockito.when(clinicaRepository.findById(2)).thenReturn(Optional.of(novaClinica));
            Mockito.when(fisioterapeutaRepository.findById(2)).thenReturn(Optional.of(novoFisio));
            Mockito.when(agendamentoRepository.save(existente)).thenReturn(existente);

            // When
            Agendamento resultado = agendamentoService.atualizar(id, entity);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertAll(
                    () -> Assertions.assertEquals(2, resultado.getClinica().getId_clinica()),
                    () -> Assertions.assertEquals(2, resultado.getFisioterapeuta().getId_fisioterapeuta()),
                    () -> Assertions.assertEquals(LocalDate.of(2026, 12, 10), resultado.getData_agendamento()),
                    () -> Assertions.assertEquals(LocalTime.of(14, 0), resultado.getHora_inicio()),
                    () -> Assertions.assertEquals(LocalTime.of(15, 0), resultado.getHora_fim()),
                    () -> Assertions.assertEquals(RegraStatusEnum.INATIVO, resultado.getStatus_agendamento()),
                    () -> Assertions.assertEquals("Observação atualizada", resultado.getObservacao())
            );
        }

        @Test
        @DisplayName("Deve validar agendamento inexistente")
        void deveLancarExceptionQuandoAgendamentoNaoEncontrado() {

            // Given
            Integer idInexistente = 99;
            Agendamento entity = criarAgendamentoTeste();

            Mockito.when(agendamentoRepository.findById(idInexistente))
                    .thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> agendamentoService.atualizar(idInexistente, entity)
            );

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assertions.assertEquals("Agendamento não encontrado.", exception.getReason());
        }

        @Test
        @DisplayName("Deve validae quando houver conflito de horário")
        void deveLancarExceptionQuandoHouverConflitoAoAtualizar() {

            // Given
            Integer id = 1;
            Agendamento existente = criarAgendamentoTeste();
            Agendamento novosDados = criarAgendamentoTeste();

            Mockito.when(agendamentoRepository.findById(id))
                    .thenReturn(Optional.of(existente));

            Mockito.when(agendamentoRepository.existeConflito(
                    Mockito.eq(1),
                    Mockito.eq(novosDados.getData_agendamento()),
                    Mockito.eq(novosDados.getHora_inicio()),
                    Mockito.eq(novosDados.getHora_fim()),
                    Mockito.eq(RegraStatusEnum.INATIVO),
                    Mockito.eq(id)
            )).thenReturn(true);

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> agendamentoService.atualizar(id, novosDados)
            );

            // Then
            Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
            Assertions.assertEquals("Existe conflito de horário para este fisioterapeuta.", exception.getReason());
        }
    }

    @Nested
    @DisplayName("Testes do método cancelar")
    class Cancelar {

        @Test
        @DisplayName("Deve cancelar agendamento alterando status para CANCELADO")
        void deveCancelarAgendamentoComSucesso() {

            // Given
            Integer id = 1;
            Agendamento existente = criarAgendamentoTeste();

            Mockito.when(agendamentoRepository.findById(id))
                    .thenReturn(Optional.of(existente));

            // When
            agendamentoService.cancelar(id);

            // Then
            Assertions.assertEquals(RegraStatus.AGENDAMENTO_CANCELADO, existente.getStatus_agendamento());
            Mockito.verify(agendamentoRepository, Mockito.times(1)).save(existente);
        }

        @Test
        @DisplayName("Deve validar agendamento inexistente ao tentar cancelar")
        void deveLancarExceptionQuandoCancelarIdInexistente() {

            // Given
            Integer idInexistente = 99;
            Mockito.when(agendamentoRepository.findById(idInexistente))
                    .thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> agendamentoService.cancelar(idInexistente)
            );

            // Then
            Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        }

        @Test
        @DisplayName("Deve validar agendamento já cancelado")
        void deveLancarExceptionQuandoAgendamentoJaCancelado() {

            // Given
            Agendamento agendamento = criarAgendamentoTeste();
            agendamento.setStatus_agendamento(RegraStatusEnum.INATIVO);
            Integer id = 1;

            Optional<Agendamento> agendamentoOptional = Optional.of(agendamento);

            Mockito.when(agendamentoRepository.findById(id))
                    .thenReturn(agendamentoOptional);

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> agendamentoService.cancelar(id)
            );

            // Then
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
            Assertions.assertEquals("Agendamento já está cancelado.", exception.getReason());
        }
    }

}