package projeto.fisio4life.fenix.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import projeto.fisio4life.fenix.Entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    boolean existsByCpf(String cpf);
}
