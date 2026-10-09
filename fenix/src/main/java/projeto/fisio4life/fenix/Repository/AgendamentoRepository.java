package projeto.fisio4life.fenix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.fisio4life.fenix.Entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    @org.springframework.data.jpa.repository.Query("""
        SELECT COUNT(a) > 0
        FROM Agendamento a
        WHERE a.fisioterapeuta.idFisioterapeuta = :idFisioterapeuta
          AND a.dataAgendamento = :dataAgendamento
          AND a.horaInicio < :horaFim
          AND a.horaFim > :horaInicio
          AND a.statusAgendamento <> :statusCancelado
          AND (:idAgendamento IS NULL OR a.idAgendamento <> :idAgendamento)
        """)
    boolean existeConflito(
            @org.springframework.data.repository.query.Param("idFisioterapeuta") Integer idFisioterapeuta,
            @org.springframework.data.repository.query.Param("dataAgendamento") java.time.LocalDate dataAgendamento,
            @org.springframework.data.repository.query.Param("horaInicio") java.time.LocalTime horaInicio,
            @org.springframework.data.repository.query.Param("horaFim") java.time.LocalTime horaFim,
            @org.springframework.data.repository.query.Param("statusCancelado") Integer statusCancelado,
            @org.springframework.data.repository.query.Param("idAgendamento") Integer idAgendamento
    );
}