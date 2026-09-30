package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Table
public class Fisioterapeuta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_fisioterapeuta;
    @OneToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
    @Column(unique = true)
    private String crefito;
    private String especialidade;
    @Column(unique = true)
    private String cnpj;

    public Fisioterapeuta() {
    }

    public Fisioterapeuta(Integer id_fisioterapeuta, Usuario usuario, String crefito, String especialidade, String cnpj) {
        this.id_fisioterapeuta = id_fisioterapeuta;
        this.usuario = usuario;
        this.crefito = crefito;
        this.especialidade = especialidade;
        this.cnpj = cnpj;
    }

    public Integer getId_fisioterapeuta() {
        return id_fisioterapeuta;
    }

    public void setId_fisioterapeuta(Integer id_fisioterapeuta) {
        this.id_fisioterapeuta = id_fisioterapeuta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCrefito() {
        return crefito;
    }

    public void setCrefito(String crefito) {
        this.crefito = crefito;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}

