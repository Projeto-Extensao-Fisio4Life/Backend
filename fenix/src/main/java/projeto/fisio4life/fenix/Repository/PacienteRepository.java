package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    boolean existsByCpf(String cpf);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Paciente p WHERE p.cpf = :cpf AND (:idAtual IS NULL OR p.id_paciente <> :idAtual)")
    boolean existeCpfEmOutroPaciente(@Param("cpf") String cpf, @Param("idAtual") Integer idAtual);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Paciente p WHERE p.usuario.id_usuario = :idUsuario AND (:idAtual IS NULL OR p.id_paciente <> :idAtual)")
    boolean existeOutroPacienteParaUsuario(@Param("idUsuario") Integer idUsuario, @Param("idAtual") Integer idAtual);
}
