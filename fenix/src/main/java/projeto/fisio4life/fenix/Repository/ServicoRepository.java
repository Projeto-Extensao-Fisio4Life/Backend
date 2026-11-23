package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Servico;

import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    Optional<Servico> findByNomeIgnoreCase(String nome);
}
