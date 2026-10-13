package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.fisio4life.fenix.Entity.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {

}