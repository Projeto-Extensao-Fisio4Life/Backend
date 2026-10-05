package projeto.fisio4life.fenix.Dto.UsuarioDtoTESTED;

public class UsuarioResumedDtoTESTE {
    private Integer id_usuario;
    private String nome;
    private String tipo_usuario;
    private Integer status_usuario;

    public UsuarioResumedDtoTESTE() {
    }

    public UsuarioResumedDtoTESTE(Integer id_usuario, String nome, String tipo_usuario, Integer status_usuario) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.tipo_usuario = tipo_usuario;
        this.status_usuario = status_usuario;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Integer getStatus_usuario() {
        return status_usuario;
    }

    public void setStatus_usuario(Integer status_usuario) {
        this.status_usuario = status_usuario;
    }
}
