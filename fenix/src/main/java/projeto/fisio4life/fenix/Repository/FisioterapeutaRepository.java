package projeto.fisio4life.fenix.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;

public interface FisioterapeutaRepository extends JpaRepository<Fisioterapeuta, Integer> {

    boolean existsByCrefito(String crefito);
    boolean existsByCnpj(String cnpj);
}