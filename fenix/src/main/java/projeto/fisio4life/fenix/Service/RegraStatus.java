package projeto.fisio4life.fenix.Service;

/**
 * Códigos de status provisórios. O SQL define TINYINT, mas não documenta os códigos.
 * Ajuste conforme a convenção oficial do projeto.
 */
public final class RegraStatus {
    private RegraStatus() {}
    public static final byte AGENDAMENTO_CANCELADO = 2;
    public static final byte SERVICO_ATIVO = 1;
}
