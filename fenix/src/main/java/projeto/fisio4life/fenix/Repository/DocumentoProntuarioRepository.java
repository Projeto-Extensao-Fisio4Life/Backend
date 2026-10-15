package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.DocumentoProntuario;

public interface DocumentoProntuarioRepository extends JpaRepository<DocumentoProntuario, Integer> {
    @Query("SELECT d FROM DocumentoProntuario d WHERE d.prontuario.id_prontuario = :idProntuario ORDER BY d.id_documento")
    java.util.List<DocumentoProntuario> buscarPorProntuario(@Param("idProntuario") Integer idProntuario);
}
