package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.EscalaProntuario;

public interface EscalaProntuarioRepository extends JpaRepository<EscalaProntuario, Integer> {
    @Query("SELECT ep FROM EscalaProntuario ep WHERE ep.prontuario.id_prontuario = :idProntuario ORDER BY ep.data_resposta DESC")
    java.util.List<EscalaProntuario> buscarPorProntuario(@Param("idProntuario") Integer idProntuario);

    @Query("SELECT ep FROM EscalaProntuario ep WHERE ep.escala.id_escala = :idEscala ORDER BY ep.data_resposta DESC")
    java.util.List<EscalaProntuario> buscarPorEscala(@Param("idEscala") Integer idEscala);
}
