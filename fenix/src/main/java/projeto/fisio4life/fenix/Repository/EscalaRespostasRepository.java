package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.EscalaRespostas;

public interface EscalaRespostasRepository extends JpaRepository<EscalaRespostas, Integer> {
    @Query("SELECT er FROM EscalaRespostas er WHERE er.escala_prontuario.id_escala_prontuario = :idEscalaProntuario ORDER BY er.id_escala_respostas")
    java.util.List<EscalaRespostas> buscarPorAplicacao(@Param("idEscalaProntuario") Integer idEscalaProntuario);
}
