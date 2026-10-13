package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Agendamento;
import java.time.LocalDate;
import java.time.LocalTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    @Query("""
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
        FROM Agendamento a
        WHERE a.fisioterapeuta.id_fisioterapeuta = :idFisioterapeuta
          AND a.data_agendamento = :dataAgendamento
          AND a.hora_inicio < :horaFim
          AND a.hora_fim > :horaInicio
          AND a.status <> :statusCancelado
          AND (:idAgendamento IS NULL OR a.id_agendamento <> :idAgendamento)
        """)
    boolean existeConflito(
            @Param("idFisioterapeuta") Integer idFisioterapeuta,
            @Param("dataAgendamento") LocalDate dataAgendamento,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFim") LocalTime horaFim,
            @Param("statusCancelado") Integer statusCancelado,
            @Param("idAgendamento") Integer idAgendamento);
}