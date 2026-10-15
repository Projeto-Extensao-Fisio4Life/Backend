package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Prontuario p WHERE p.paciente.id_paciente = :idPaciente AND (:idAtual IS NULL OR p.id_prontuario <> :idAtual)")
    boolean existeOutroProntuarioDoPaciente(@Param("idPaciente") Integer idPaciente, @Param("idAtual") Integer idAtual);
}
