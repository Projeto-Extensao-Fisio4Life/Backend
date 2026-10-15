package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Evolucao;

public interface EvolucaoRepository extends JpaRepository<Evolucao, Integer> {
    @Query("SELECT e FROM Evolucao e WHERE e.sessao.id_sessao = :idSessao ORDER BY e.data_evolucao DESC")
    java.util.List<Evolucao> buscarPorSessao(@Param("idSessao") Integer idSessao);

    @Query("SELECT e FROM Evolucao e WHERE e.prontuario.id_prontuario = :idProntuario ORDER BY e.data_evolucao DESC")
    java.util.List<Evolucao> buscarPorProntuario(@Param("idProntuario") Integer idProntuario);
}
