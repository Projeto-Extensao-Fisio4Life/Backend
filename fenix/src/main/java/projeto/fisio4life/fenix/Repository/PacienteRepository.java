package projeto.fisio4life.fenix.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Usuario;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    boolean existsByCpf(String cpf);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Paciente p WHERE p.usuario.id_usuario = :idUsuario AND (:idAtual IS NULL OR p.id_paciente <> :idAtual)")
    boolean existeOutroPacienteParaUsuario(@Param("idUsuario") Integer idUsuario, @Param("idAtual") Integer idAtual);

    Paciente getByUsuario(Usuario usuarioCadastrado);
}
