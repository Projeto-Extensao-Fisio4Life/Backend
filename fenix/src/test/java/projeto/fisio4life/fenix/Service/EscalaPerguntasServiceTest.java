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
import projeto.fisio4life.fenix.Entity.Clinica;
import projeto.fisio4life.fenix.Entity.Escala;
import projeto.fisio4life.fenix.Entity.EscalaPerguntas;
import projeto.fisio4life.fenix.Repository.EscalaPerguntasRepository;
import projeto.fisio4life.fenix.Repository.EscalaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EscalaPerguntasServiceTest {

    @Mock
    private EscalaPerguntasRepository escalaPerguntasRepository;

    @Mock
    private EscalaRepository escalaRepository;

    @InjectMocks
    private EscalaPerguntasService escalaPerguntasService;

    private EscalaPerguntas criarEscalPerguntasTeste(){
        EscalaPerguntas escalaPerguntas = new EscalaPerguntas();

        escalaPerguntas.setId_escala_perguntas(1);
        escalaPerguntas.setTitulo_pergunta("Como está o nivel da sua dor no atual momento");
        escalaPerguntas.setTipo_dado("Numerico");

        Escala escala = new Escala();
        escala.setId_escala(1);
        escala.setNome("Escala pós-operatorio joelho");
        escala.setDescricao("Escala para o pós-operatorio do Pedro Henrique");

        escalaPerguntas.setEscala(escala);

        return escalaPerguntas;
    }

    @Nested
    @DisplayName("Testes do método salvar")
    class Salvar {
        @Test
        @DisplayName("Deve salvar corretamente")

        void deveSalvarCorretamente(){
            // Given
            EscalaPerguntas escalaPerguntas = criarEscalPerguntasTeste();

            Mockito.when(escalaRepository.findById(escalaPerguntas.getEscala().getId_escala())).thenReturn(Optional.of(escalaPerguntas.getEscala()));
            Mockito.when(escalaPerguntasRepository.save(Mockito.any(EscalaPerguntas.class))).thenAnswer(i -> i.getArgument(0));

            // When
            EscalaPerguntas resultado = escalaPerguntasService.salvar(escalaPerguntas);

            // Then
            Mockito.verify(escalaPerguntasRepository, Mockito.times(1)).save(escalaPerguntas);
            Assertions.assertEquals(1, resultado.getId_escala_perguntas());

        }

    }


    @Nested
    @DisplayName("Testes do método buscar")
    class BuscarPorId {

        @Test
        @DisplayName("Deve voltar corretamente a pergunta da escala em conjunto com seu ID")
        void BuscarViaId(){
            // Given
            EscalaPerguntas escalaPerguntas = criarEscalPerguntasTeste();

            Mockito.when(escalaPerguntasRepository.findById(1)).thenReturn(Optional.of(escalaPerguntas));
            // When
            EscalaPerguntas resultado = escalaPerguntasService.buscarPorId(1);

            // Then
            Assertions.assertEquals(1, resultado.getId_escala_perguntas());

        }

        @Test
        @DisplayName("Quando não existe a clinica com o ID deve retornarr 404")
        void deveRetornar404(){
            Mockito.when(escalaPerguntasRepository.findById(99)).thenReturn(Optional.empty());


            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () ->  escalaPerguntasService.buscarPorId(99));


        }

    }

    @Nested
    @DisplayName("Testes do método atualizar")
    class validacoes {

        @Test
        @DisplayName("Deve atualizar corretamente")
        void AtualizarCorretamente(){
            Integer id = 1;
            EscalaPerguntas existente = criarEscalPerguntasTeste();

            EscalaPerguntas novosDadosEscala = new EscalaPerguntas();
            novosDadosEscala.setId_escala_perguntas(id);
            novosDadosEscala.setTitulo_pergunta("Como está o nivel da sua dor no atual momento");
            novosDadosEscala.setTipo_dado("Numerico");

            Escala escala = new Escala();
            escala.setId_escala(1);
            escala.setNome("Escala pós-operatorio joelho");
            escala.setDescricao("Escala para o pós-operatorio do Matheus Valle");

            novosDadosEscala.setEscala(escala);

            Mockito.when(escalaPerguntasRepository.findById(id)).thenReturn(Optional.of(existente));

            Mockito.when(escalaRepository.findById(novosDadosEscala.getEscala().getId_escala())).thenReturn(Optional.of(novosDadosEscala.getEscala()));
            Mockito.when(escalaPerguntasRepository.save(Mockito.any(EscalaPerguntas.class))).thenAnswer(i -> i.getArgument(0));

            EscalaPerguntas resultado = escalaPerguntasService.atualizar(id, novosDadosEscala);

            Assertions.assertNotNull(resultado);
            Mockito.verify(escalaPerguntasRepository, Mockito.times(1)).save(existente);
            Assertions.assertEquals(1, resultado.getId_escala_perguntas());

        }

        @Test
        @DisplayName("Deve lançar NOT_FOUD ao tentar atualizar paciente inexistente")
        void deveLancarExeptionAoAtualizar(){
            EscalaPerguntas novasPerguntas = criarEscalPerguntasTeste();
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () ->  escalaPerguntasService.atualizar(99, novasPerguntas));


        }

    }

    @Nested
    @DisplayName("Testes do método excluir")
    class excluir {
        @Test
        @DisplayName("Deve excluir corretamente")
        void deeExcluirPerguntaEscalaComSucesso(){
            Integer id = 1;
            EscalaPerguntas existente = criarEscalPerguntasTeste();

            Mockito.when(escalaPerguntasRepository.findById(id)).thenReturn(Optional.of(existente));

            escalaPerguntasService.excluir(id);


            Mockito.verify(escalaPerguntasRepository, Mockito.times(1)).delete(existente);
            Mockito.verify(escalaPerguntasRepository, Mockito.times(1)).flush();

        }


        @Test
        @DisplayName("Deve lançar NOT_FOUD ao tentar excluir paciente inexistente")
        void deveLancarExeptionAoAtualizar(){
            EscalaPerguntas novasPerguntas = criarEscalPerguntasTeste();
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () ->  escalaPerguntasService.excluir(99));
        }

    }

    @Nested
    @DisplayName("Buscar todas as escalas")
    class buscarPorEscala{
        @Test
        @DisplayName("Deve buscar por escala")
        void DeveRetornarTodasAsPerguntas(){
            // Given
            Integer idEscala = 1;
            EscalaPerguntas pergunta = criarEscalPerguntasTeste();

            Mockito.when(escalaRepository.existsById(idEscala)).thenReturn(true);
            Mockito.when(escalaPerguntasRepository.buscarPorEscala(idEscala)).thenReturn(List.of(pergunta));

            List<EscalaPerguntas> resultado = escalaPerguntasService.listarPorEscala(idEscala);

            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.size());
            Assertions.assertEquals(pergunta, resultado.get(0));
            Mockito.verify(escalaPerguntasRepository, Mockito.times(1)).buscarPorEscala(idEscala);
        }

        @Test
        @DisplayName("Caso a escala não exista deve retornar NOT FOUND")
        void DeveRetornar404CasoNaoExistaEscala(){
            Integer idEscala = 99;

            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> escalaPerguntasService.listarPorEscala(idEscala));
        }

    }
}