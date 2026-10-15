package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.EscalaPerguntas;

public interface EscalaPerguntasRepository extends JpaRepository<EscalaPerguntas, Integer> {
    @Query("SELECT ep FROM EscalaPerguntas ep WHERE ep.escala.id_escala = :idEscala ORDER BY ep.id_escala_perguntas")
    java.util.List<EscalaPerguntas> buscarPorEscala(@Param("idEscala") Integer idEscala);
}
