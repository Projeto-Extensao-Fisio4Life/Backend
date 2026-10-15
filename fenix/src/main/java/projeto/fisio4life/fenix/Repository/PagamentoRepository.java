package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    @Query("SELECT p FROM Pagamento p WHERE p.paciente.id_paciente = :idPaciente ORDER BY p.id_pagamento DESC")
    java.util.List<Pagamento> buscarPorPaciente(@Param("idPaciente") Integer idPaciente);

    @Query("SELECT p FROM Pagamento p WHERE p.servico.id_servico = :idServico ORDER BY p.id_pagamento DESC")
    java.util.List<Pagamento> buscarPorServico(@Param("idServico") Integer idServico);
}
