package projeto.fisio4life.fenix.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    boolean existsByCpf(String cpf);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Paciente p WHERE p.cpf = :cpf AND p.id_paciente <> :idPaciente")
    boolean existeCpfDeOutroPaciente(@Param("cpf") String cpf, @Param("idPaciente") Integer idPaciente);
}
