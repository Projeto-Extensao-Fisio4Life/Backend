package projeto.fisio4life.fenix.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projeto.fisio4life.fenix.Entity.PacienteClinica;

public interface PacienteClinicaRepository extends JpaRepository<PacienteClinica, Integer> {

    boolean existsByClinicaIdClinicaAndPacienteIdPaciente(Integer idClinica, Integer idPaciente);
    java.util.List<PacienteClinica> findByClinicaIdClinica(Integer idClinica);
}
