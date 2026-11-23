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
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.web.server.ResponseStatusException;
import projeto.fisio4life.fenix.Entity.Escala;
import projeto.fisio4life.fenix.Entity.EscalaPerguntas;
import projeto.fisio4life.fenix.Repository.EscalaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EscalaServiceTest {

    @Mock
    private EscalaRepository escalaRepository;

    @InjectMocks
    private EscalaService escalaService;

    private Escala criarEscalaTeste(){
        Escala escala = new Escala();
        escala.setId_escala(1);
        escala.setNome("Escala para Pedro Henrique");
        escala.setDescricao("escala voltada para ao pedro henrique");

        return escala;
    }

    @Nested
    @DisplayName("Testes do método salvar")
    class Salvar {
        @Test
        @DisplayName("Deve salvar corretamente")

        void deveSalvarCorretamente(){
            // Given
            Escala escala = criarEscalaTeste();

            Mockito.when(escalaRepository.save(Mockito.any(Escala.class))).thenAnswer(i -> i.getArgument(0));

            // When
            Escala resultado = escalaService.salvar(escala);

            // Then
            Mockito.verify(escalaRepository, Mockito.times(1)).save(escala);
            Assertions.assertEquals(1, resultado.getId_escala());

        }

    }


    @Nested
    @DisplayName("Testes do método buscar")
    class BuscarPorId {

        @Test
        @DisplayName("Deve voltar corretamente a pergunta da escala em conjunto com seu ID")
        void BuscarViaId(){
            // Given
            Escala escala = criarEscalaTeste();

            Mockito.when(escalaRepository.findById(1)).thenReturn(Optional.of(escala));
            // When
            Escala resultado = escalaService.buscarPorId(1);

            // Then
            Assertions.assertEquals(1, resultado.getId_escala());

        }

        @Test
        @DisplayName("Quando não existe a clinica com o ID deve retornarr 404")
        void deveRetornar404(){
            Mockito.when(escalaRepository.findById(99)).thenReturn(Optional.empty());


            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () ->  escalaService.buscarPorId(99));


        }

    }

    @Nested
    @DisplayName("Testes do método atualizar")
    class validacoes {

        @Test
        @DisplayName("Deve atualizar corretamente")
        void AtualizarCorretamente(){
            Integer id = 1;
            Escala existente = criarEscalaTeste();

            Escala escala = new Escala();
            escala.setId_escala(1);
            escala.setNome("Escala pós-operatorio joelho");
            escala.setDescricao("Escala para o pós-operatorio do Matheus Valle");

            Mockito.when(escalaRepository.findById(id)).thenReturn(Optional.of(existente));
            Mockito.when(escalaRepository.save(Mockito.any(Escala.class))).thenAnswer(i -> i.getArgument(0));

            Escala resultado = escalaService.atualizar(id,escala);

            Assertions.assertNotNull(resultado);
            Mockito.verify(escalaRepository, Mockito.times(1)).save(existente);
            Assertions.assertEquals(1, resultado.getId_escala());

        }

        @Test
        @DisplayName("Deve lançar NOT_FOUD ao tentar atualizar paciente inexistente")
        void deveLancarExeptionAoAtualizar(){
            Escala novasPerguntas = criarEscalaTeste();
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () ->  escalaService.atualizar(99, novasPerguntas));


        }

    }

    @Nested
    @DisplayName("Testes do método excluir")
    class excluir {
        @Test
        @DisplayName("Deve excluir corretamente")
        void deveExcluirPerguntaEscalaComSucesso(){
            Integer id = 1;
            Escala existente = criarEscalaTeste();

            Mockito.when(escalaRepository.findById(id)).thenReturn(Optional.of(existente));

            escalaService.excluir(id);


            Mockito.verify(escalaRepository, Mockito.times(1)).delete(existente);
            Mockito.verify(escalaRepository, Mockito.times(1)).flush();

        }


        @Test
        @DisplayName("Deve lançar NOT_FOUD ao tentar excluir paciente inexistente")
        void deveLancarExeptionAoExcluir(){
            Escala novasPerguntas = criarEscalaTeste();
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () ->  escalaService.excluir(99));
        }

    }



}