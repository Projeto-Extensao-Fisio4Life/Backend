package projeto.fisio4life.fenix.Enum;

public enum TipoUsuarioEnum {
    PACIENTE("PACIENTE"),
    FISIOTERAPEUTA("FISIOTERAPEUTA"),
    DONO_CLINICA("DONO_CLINICA");

    private final String descricao;

    TipoUsuarioEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
