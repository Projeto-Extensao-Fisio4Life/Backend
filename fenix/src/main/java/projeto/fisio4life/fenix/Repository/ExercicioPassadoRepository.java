package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.ExercicioPassado;

public interface ExercicioPassadoRepository extends JpaRepository<ExercicioPassado, Integer> {
    @Query("SELECT ep FROM ExercicioPassado ep WHERE ep.evolucao.id_evolucao = :idEvolucao ORDER BY ep.id_exercicio_passado")
    java.util.List<ExercicioPassado> buscarPorEvolucao(@Param("idEvolucao") Integer idEvolucao);
}
