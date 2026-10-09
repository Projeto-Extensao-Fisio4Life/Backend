package projeto.fisio4life.fenix.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projeto.fisio4life.fenix.Entity.Clinica;

public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {

    boolean existsByCnpj(String cnpj);
}