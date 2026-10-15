package projeto.fisio4life.fenix.Enum;

public enum TipoUsuarioEnum {
    PACIENTE("paciente"),
    FISIOTERAPEUTA("fisioterapeuta"),
    DONO_CLINICA("dono_clinica");

    private final String descricao;

    TipoUsuarioEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
