package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.fisio4life.fenix.Entity.Agendamento;
import projeto.fisio4life.fenix.Enum.RegraStatusEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    @Query("""
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
        FROM Agendamento a
        WHERE a.fisioterapeuta.id_fisioterapeuta = :idFisioterapeuta
          AND a.data_agendamento = :data
          AND a.hora_inicio < :horaFim
          AND a.hora_fim > :horaInicio
          AND a.status_agendamento <> :statusCancelado
          AND (:idAtual IS NULL OR a.id_agendamento <> :idAtual)
        """)
    boolean existeConflito(@Param("idFisioterapeuta") Integer idFisioterapeuta,
                           @Param("data") LocalDate data,
                           @Param("horaInicio") LocalTime horaInicio,
                           @Param("horaFim") LocalTime horaFim,
                           @Param("statusCancelado") RegraStatusEnum statusCancelado,
                           @Param("idAtual") Integer idAtual);
}
