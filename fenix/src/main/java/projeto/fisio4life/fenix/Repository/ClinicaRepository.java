package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Clinica;

public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Clinica c WHERE c.cnpj = :cnpj AND (:idAtual IS NULL OR c.id_clinica <> :idAtual)")
    boolean existeCnpjEmOutra(@Param("cnpj") String cnpj, @Param("idAtual") Integer idAtual);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Clinica c WHERE c.endereco.id_endereco = :idEndereco AND (:idAtual IS NULL OR c.id_clinica <> :idAtual)")
    boolean existeOutraClinicaNoEndereco(@Param("idEndereco") Integer idEndereco, @Param("idAtual") Integer idAtual);
}
