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
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.*;
import projeto.fisio4life.fenix.Enum.StatusUsuarioEnum;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;
import projeto.fisio4life.fenix.Repository.EscalaPerguntasRepository;
import projeto.fisio4life.fenix.Repository.EscalaProntuarioRepository;
import projeto.fisio4life.fenix.Repository.EscalaRespostasRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EscalaRespostasServiceTest {

    @Mock
    private EscalaRespostasRepository escalaRespostasRepository;

    @Mock
    private EscalaPerguntasRepository escalaPerguntasRepository;

    @Mock
    private EscalaProntuarioRepository escalaProntuarioRepository;

    @InjectMocks
    private EscalaRespostasService escalaRespostasService;

    public EscalaRespostas criarEscalaRespostasTeste(){

        // Criar usuario
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setNome("João Pedro da Silva");
        usuario.setTipo_usuario(TipoUsuarioEnum.PACIENTE);
        usuario.setStatus_usuario(StatusUsuarioEnum.ATIVO);

        // Criar Paciente
        Paciente paciente = new Paciente();
        paciente.setId_paciente(1);
        paciente.setUsuario(usuario);
        paciente.setCpf("12345678901");
        paciente.setPermite_atendimento_grupo(true);

        // criar prontuario
        Prontuario prontuario = new Prontuario();
        prontuario.setId_prontuario(1);
        prontuario.setPaciente(paciente);
        prontuario.setStatus_prontuario((byte) 1);
        prontuario.setData_abertura(LocalDateTime.now());
        prontuario.setObservacao_inicial("Paciente recem operado");

        // criar escala
        Escala escala = new Escala();
        escala.setId_escala(1);
        escala.setNome("Escala para pedro henrique");
        escala.setDescricao("Escala para o pos operatorio de pedro henrique");

        // criar escala pergunta
        EscalaPerguntas escalaPerguntas = new EscalaPerguntas();
        escalaPerguntas.setId_escala_perguntas(1);
        escalaPerguntas.setEscala(escala);
        escalaPerguntas.setTitulo_pergunta("Qual é o seu nivel de dor");
        escalaPerguntas.setTipo_dado("Texto");


        // Criar escala prontuario
        EscalaProntuario escalaProntuario = new EscalaProntuario();
        escalaProntuario.setId_escala_prontuario(1);
        escalaProntuario.setProntuario(prontuario);
        escalaProntuario.setEscala(escala);
        escalaProntuario.setData_resposta(LocalDateTime.now());

        // Criar escala resposta
        EscalaRespostas escalaRespostas = new EscalaRespostas();
        escalaRespostas.setId_escala_respostas(1);
        escalaRespostas.setEscala_prontuario(escalaProntuario);
        escalaRespostas.setEscala_perguntas(escalaPerguntas);
        escalaRespostas.setValor_resposta("Alta");

        return escalaRespostas;
    }


    @Nested
    @DisplayName("Testes do método salvar")
    class Salvar {
        @Test
        @DisplayName("Deve salvar corretamente")

        void deveSalvarCorretamente(){
            // Given
            EscalaRespostas escalaRespostas = criarEscalaRespostasTeste();

            Mockito.when(escalaProntuarioRepository.findById(escalaRespostas.getEscala_prontuario().getId_escala_prontuario())).thenReturn(Optional.of(escalaRespostas.getEscala_prontuario()));

            Mockito.when(escalaPerguntasRepository.findById(escalaRespostas.getEscala_perguntas().getId_escala_perguntas())).thenReturn(Optional.of(escalaRespostas.getEscala_perguntas()));

            Mockito.when(escalaRespostasRepository.save(Mockito.any(EscalaRespostas.class)))
                    .thenAnswer(i -> i.getArgument(0));
            // When
            EscalaRespostas resultado = escalaRespostasService.salvar(escalaRespostas);

            // Then
            Mockito.verify(escalaRespostasRepository, Mockito.times(1)).save(escalaRespostas);
            Assertions.assertEquals(1, resultado.getId_escala_respostas());

        }

    }


    @Nested
    @DisplayName("Testes do método buscar")
    class BuscarPorId {

        @Test
        @DisplayName("Deve voltar corretamente a pergunta da escala em conjunto com seu ID")
        void BuscarViaId(){
            // Given
            EscalaRespostas escalaRespostas = criarEscalaRespostasTeste();

            Mockito.when(escalaRespostasRepository.findById(1)).thenReturn(Optional.of(escalaRespostas));
            // When
            EscalaRespostas resultado = escalaRespostasService.buscarPorId(1);

            // Then
            Assertions.assertEquals(1, resultado.getId_escala_respostas());

        }

        @Test
        @DisplayName("Quando não existe a clinica com o ID deve retornarr 404")
        void deveRetornar404(){
            Mockito.when(escalaRespostasRepository.findById(99)).thenReturn(Optional.empty());


            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () ->  escalaRespostasService.buscarPorId(99));


        }

        @Test
        @DisplayName("Deve retornar todas as respostas corretamente")
        void deveRetornarTodasAsRespostas() {

            // Given
            Mockito.when(escalaRespostasRepository.findAll()).thenReturn(List.of(criarEscalaRespostasTeste()));

            // When
            List<EscalaRespostas> resultado = escalaRespostasService.listarTodos();

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.size());

        }

        @Test
        @DisplayName("Deve retornar lista de clinicas vazia")
        void deveRetornarTodasAsRespostasVazia() {

            // Given
            Mockito.when(escalaRespostasRepository.findAll()).thenReturn(List.of());

            // When
            List<EscalaRespostas> resultado = escalaRespostasService.listarTodos();

            // Then
            Assertions.assertTrue(resultado.isEmpty());
        }


        @Test
        @DisplayName("Deve buscar por aplicacao")
        void deveRetornarTodasPorAplicacao(){

            Integer idEscalaPronduario = 1;
            EscalaRespostas escalaRespostas = criarEscalaRespostasTeste();

            Mockito.when(escalaProntuarioRepository.existsById(idEscalaPronduario)).thenReturn(true);

            Mockito.when(escalaRespostasRepository.buscarPorAplicacao(idEscalaPronduario)).thenReturn(List.of(escalaRespostas));

            List<EscalaRespostas> resultado = escalaRespostasService.listarPorAplicacao(idEscalaPronduario);

            Mockito.verify(escalaProntuarioRepository, Mockito.times(1)).existsById(idEscalaPronduario);
            Mockito.verify(escalaRespostasRepository, Mockito.times(1)).buscarPorAplicacao(idEscalaPronduario);
            Assertions.assertEquals(1, resultado.size());

        }

    }

    @Nested
    @DisplayName("Testes do método atualizar")
    class validacoes {

        @Test
        @DisplayName("Deve atualizar corretamente")
        void AtualizarCorretamente(){
            Integer id = 1;
            EscalaRespostas existente = criarEscalaRespostasTeste();

            EscalaRespostas novosDadosEscala = new EscalaRespostas();
            novosDadosEscala.setId_escala_respostas(existente.getId_escala_respostas());
            novosDadosEscala.setValor_resposta("Baixo");
            novosDadosEscala.setEscala_prontuario(existente.getEscala_prontuario());
            novosDadosEscala.setEscala_perguntas(existente.getEscala_perguntas());

            Mockito.when(escalaRespostasRepository.findById(id)).thenReturn(Optional.of(existente));

            Mockito.when(escalaProntuarioRepository.findById(novosDadosEscala.getEscala_prontuario().getId_escala_prontuario())).thenReturn(Optional.of(novosDadosEscala.getEscala_prontuario()));

            Mockito.when(escalaPerguntasRepository.findById(novosDadosEscala.getEscala_perguntas().getId_escala_perguntas())).thenReturn(Optional.of(novosDadosEscala.getEscala_perguntas()));

            Mockito.when(escalaRespostasRepository.save(Mockito.any(EscalaRespostas.class))).thenAnswer(i -> i.getArgument(0));

            EscalaRespostas resultado = escalaRespostasService.atualizar(id, novosDadosEscala);

            Assertions.assertNotNull(resultado);
            Mockito.verify(escalaRespostasRepository, Mockito.times(1)).save(existente);
            Assertions.assertEquals(1, resultado.getId_escala_respostas());

        }

        @Test
        @DisplayName("Deve lançar NOT_FOUD ao tentar atualizar paciente inexistente")
        void deveLancarExeptionAoAtualizar(){
            EscalaRespostas novasPerguntas = criarEscalaRespostasTeste();
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () ->  escalaRespostasService.atualizar(99, novasPerguntas));

        }

    }

    @Nested
    @DisplayName("Testes do método excluir")
    class excluir {
        @Test
        @DisplayName("Deve excluir corretamente")
        void deeExcluirPerguntaEscalaComSucesso(){
            Integer id = 1;
            EscalaRespostas existente = criarEscalaRespostasTeste();

            Mockito.when(escalaRespostasRepository.findById(id)).thenReturn(Optional.of(existente));

            escalaRespostasService.excluir(id);


            Mockito.verify(escalaRespostasRepository, Mockito.times(1)).delete(existente);
            Mockito.verify(escalaRespostasRepository, Mockito.times(1)).flush();

        }


        @Test
        @DisplayName("Deve lançar NOT_FOUD ao tentar excluir paciente inexistente")
        void deveLancarExeptionAoAtualizar(){
            EscalaRespostas novasPerguntas = criarEscalaRespostasTeste();
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () ->  escalaRespostasService.excluir(99));
        }

    }

}