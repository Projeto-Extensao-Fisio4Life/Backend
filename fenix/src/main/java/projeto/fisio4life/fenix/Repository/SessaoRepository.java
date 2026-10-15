package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Sessao;

public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Sessao s WHERE s.agendamento.id_agendamento = :idAgendamento AND (:idAtual IS NULL OR s.id_sessao <> :idAtual)")
    boolean existeSessaoDoAgendamento(@Param("idAgendamento") Integer idAgendamento, @Param("idAtual") Integer idAtual);

    @Query("SELECT COUNT(s) FROM Sessao s WHERE s.agendamento.paciente.id_paciente = :idPaciente AND s.servico.id_servico = :idServico AND (:idAtual IS NULL OR s.id_sessao <> :idAtual)")
    Integer contarSessoesDoPacienteNoServico(@Param("idPaciente") Integer idPaciente, @Param("idServico") Integer idServico, @Param("idAtual") Integer idAtual);
}
