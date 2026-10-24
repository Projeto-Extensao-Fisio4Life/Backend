package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Usuario;

public interface FisioterapeutaRepository extends JpaRepository<Fisioterapeuta, Integer> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Fisioterapeuta f WHERE f.crefito = :crefito AND (:idAtual IS NULL OR f.id_fisioterapeuta <> :idAtual)")
    boolean existeCrefitoEmOutro(@Param("crefito") String crefito, @Param("idAtual") Integer idAtual);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Fisioterapeuta f WHERE f.cnpj = :cnpj AND (:idAtual IS NULL OR f.id_fisioterapeuta <> :idAtual)")
    boolean existeCnpjEmOutro(@Param("cnpj") String cnpj, @Param("idAtual") Integer idAtual);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Fisioterapeuta f WHERE f.usuario.id_usuario = :idUsuario AND (:idAtual IS NULL OR f.id_fisioterapeuta <> :idAtual)")
    boolean existeOutroFisioterapeutaParaUsuario(@Param("idUsuario") Integer idUsuario, @Param("idAtual") Integer idAtual);

    Fisioterapeuta findByUsuario(Usuario usuarioCadastrado);
}
