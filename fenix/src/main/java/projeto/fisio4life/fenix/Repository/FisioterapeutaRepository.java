package projeto.fisio4life.fenix.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Usuario;

public interface FisioterapeutaRepository extends JpaRepository<Fisioterapeuta, Integer> {

    boolean existsByCrefito(String crefito);
    boolean existsByCnpj(String cnpj);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Fisioterapeuta f WHERE f.usuario.id_usuario = : idUsuario AND (:idAtual IS NULL OR f.id_fisioterapeuta <> :idAtual)")
    boolean existeOutroFisioterapeutaParaUsuario(@Param("idUsuario") Integer idUsuario, @Param("idAtual") Integer idAtual);

    Fisioterapeuta getByUsuario(Usuario usuarioCadastrado);
}