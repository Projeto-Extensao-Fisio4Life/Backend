package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Servico;

import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    @Query("SELECT COUNT(s) > 0 FROM Servico s WHERE LOWER(s.nome) = LOWER(:nome) AND (:id IS NULL OR s.id_servico <> :id)")
    boolean existeNomeEmOutroServico(@Param("nome") String nome, @Param("id") Integer id);
}
