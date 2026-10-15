package projeto.fisio4life.fenix.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Usuario;

public interface FisioterapeutaRepository extends JpaRepository<Fisioterapeuta, Integer> {

    boolean existsByCrefito(String crefito);
    boolean existsByCnpj(String cnpj);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Fisioterapeuta f WHERE f.crefito = :crefito AND f.id_fisioterapeuta <> :id")
    boolean existeCrefitoDeOutro(@Param("crefito") String crefito, @Param("id") Integer id);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Fisioterapeuta f WHERE f.cnpj = :cnpj AND f.id_fisioterapeuta <> :id")
    boolean existeCnpjDeOutro(@Param("cnpj") String cnpj, @Param("id") Integer id);

    Fisioterapeuta getByUsuario(Usuario usuarioCadastrado);
}