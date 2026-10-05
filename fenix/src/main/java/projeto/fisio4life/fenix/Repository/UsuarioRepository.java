package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.fisio4life.fenix.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByNome(String nome);
    java.util.Optional<Usuario> findByNome(String nome);
}