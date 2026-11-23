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
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Repository.ClinicaRepository;
import projeto.fisio4life.fenix.Repository.EnderecoRepository;
import projeto.fisio4life.fenix.Repository.PacienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClinicaServiceTest {

    @Mock
    private ClinicaRepository clinicaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private ClinicaService clinicaService;

    private Clinica criarClinicaTeste(){
        Clinica clinica = new Clinica();
        clinica.setId_clinica(1);
        clinica.setNome("Clinica moema");
        clinica.setCnpj("11222333000191");
        clinica.setCelular("11975814532");
        clinica.setTelefone("1155297475");
        clinica.setEmail("clinicaMoema@gmail.com");

        Endereco endereco = new Endereco();
        endereco.setId_endereco(1);
        endereco.setLogradouro("Rua das Flores");
        endereco.setNumero(123);
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep("01001-000");

        clinica.setEndereco(endereco);

        return clinica;
    }

    @Nested
    @DisplayName("Testes do método salvar")
    class Salvar {
        @Test
        @DisplayName("Deve salvar corretamente")
        void deveSalvarCorretamente() {

            // Given
            Clinica clinca = criarClinicaTeste();

            Mockito.when(clinicaRepository.existeCnpjEmOutra(clinca.getCnpj(), null)).thenReturn(false);

            Mockito.when(clinicaRepository.existeOutraClinicaNoEndereco(clinca.getEndereco().getId_endereco(), null)).thenReturn(false);

            Mockito.when(enderecoRepository.findById(clinca.getEndereco().getId_endereco())).thenReturn(Optional.of(clinca.getEndereco()));

            Mockito.when(clinicaRepository.save(clinca)).thenReturn(clinca);
            // When
            Clinica resultado = clinicaService.salvar(clinca);

            // Then

            Assertions.assertEquals(1, resultado.getId_clinica());
            Mockito.verify(clinicaRepository, Mockito.times(1)).save(clinca);

        }
    }

    @Nested
    @DisplayName("Testes do método buscar")
    class BuscarPorId {

        @Test
        @DisplayName("Deve voltar corretamente a clinica em conjunto com seu ID")
        void voltarClinicaCorretamente() {

            // Given
            Clinica clinica = criarClinicaTeste();

            Mockito.when(clinicaRepository.findById(1)).thenReturn(Optional.of(clinica));

            // When
            Clinica resultado = clinicaService.buscarPorId(1);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.getId_clinica());
        }

        @Test
        @DisplayName("Quando não existe a clinica com o ID deve retornar 404")
        void deveRetornar404() {

            // Given
            Mockito.when(clinicaRepository.findById(99)).thenReturn(Optional.empty());

            // When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () -> clinicaService.buscarPorId(99));
        }

        @Test
        @DisplayName("Deve retornar todas as clinicas corretamente")
        void deveRetornarTodasAsClinicas() {

            // Given
            Mockito.when(clinicaRepository.findAll()).thenReturn(List.of(criarClinicaTeste()));

            // When
            List<Clinica> resultado = clinicaService.listarTodos();

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.size());

        }

        @Test
        @DisplayName("Deve retornar lista de clinicas vazia")
        void deveRetornarTodasAsClinicasVazia() {

            // Given
            Mockito.when(clinicaRepository.findAll()).thenReturn(List.of());

            // When
            List<Clinica> resultado = clinicaService.listarTodos();

            // Then
            Assertions.assertTrue(resultado.isEmpty());
        }

    }


    @Nested
    @DisplayName("Testes do método atualizar")
    class validacoes {

        @Test
        @DisplayName("Deve atualizar corrretamente")
        void AtualizarCorretamente() {

            // Given
            Integer id = 1;
            Clinica existente = criarClinicaTeste();

            Clinica novosDadosClinica = new Clinica();
            novosDadosClinica.setId_clinica(id);
            novosDadosClinica.setNome("Clinica Santo Amaro");
            novosDadosClinica.setCnpj("11222333000191");
            novosDadosClinica.setCelular("11975814532");
            novosDadosClinica.setTelefone("1155297475");
            novosDadosClinica.setEmail("clinicaSantoAmaro@gmail.com");


            Endereco enderecoNovo = new Endereco();
            enderecoNovo.setId_endereco(2);
            enderecoNovo.setLogradouro("Avenida Santo Amaro");
            enderecoNovo.setNumero(11);
            enderecoNovo.setBairro("Zona Sul");
            enderecoNovo.setCidade("São Paulo");
            enderecoNovo.setEstado("SP");
            enderecoNovo.setCep("01001-001");

            novosDadosClinica.setEndereco(enderecoNovo);

            Mockito.when(clinicaRepository.findById(id)).thenReturn(Optional.of(existente));

            Mockito.when(clinicaRepository.existeCnpjEmOutra(novosDadosClinica.getCnpj(), id)).thenReturn(false);

            Mockito.when(clinicaRepository.existeOutraClinicaNoEndereco(novosDadosClinica.getEndereco().getId_endereco(), id)).thenReturn(false);

            Mockito.when(enderecoRepository.findById(novosDadosClinica.getEndereco().getId_endereco())).thenReturn(Optional.of(novosDadosClinica.getEndereco()));
            Mockito.when(clinicaRepository.save(Mockito.any(Clinica.class))).thenAnswer(i -> i.getArgument(0));


            // When
            Clinica resultado = clinicaService.atualizar(id, novosDadosClinica);

            // Then
            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(1, resultado.getId_clinica());
            Assertions.assertEquals(novosDadosClinica.getNome(), resultado.getNome());
            Assertions.assertEquals(novosDadosClinica.getEndereco(), resultado.getEndereco());
            Mockito.verify(clinicaRepository, Mockito.times(1)).save(Mockito.any(Clinica.class));

        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND ao tentar atualizar paciente inexistente")
        void deveLancarException() {
            // Given
            Clinica novosDados = criarClinicaTeste();

            //When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () -> clinicaService.atualizar(99, novosDados));

        }

    }



    @Nested
    @DisplayName("Testes do método excluir")
    class excluir {
        @Test
        @DisplayName("Deve excluir corretamente")
        void deveExcluirClinicaComSucesso() {
            // Given
            Integer id = 1;
            Clinica existente = criarClinicaTeste();

            Mockito.when(clinicaRepository.findById(id)).thenReturn(Optional.of(existente));

            // When
            clinicaService.excluir(id);

            // Then
            Mockito.verify(clinicaRepository, Mockito.times(1)).delete(existente);
            Mockito.verify(clinicaRepository, Mockito.times(1)).flush();
        }

        @Test
        @DisplayName("Deve lançar NOT_FOUND ao tentar excluir clinica inexistente")
        void deveLancarExceptionExclusao(){
            // Given
            Clinica novosDados = criarClinicaTeste();

            //When
            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class, () -> clinicaService.excluir(99));
        }



    }

}