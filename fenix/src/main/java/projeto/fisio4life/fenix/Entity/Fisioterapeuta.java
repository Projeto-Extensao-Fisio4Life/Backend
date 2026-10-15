package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fisioterapeuta")
public class Fisioterapeuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fisioterapeuta", nullable = false)
    private Integer id_fisioterapeuta;

    @OneToOne
    @JoinColumn(name = "fk_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "crefito", nullable = false, length = 10)
    private String crefito;

    @Column(name = "especialidade", nullable = false, length = 45)
    private String especialidade;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    public Fisioterapeuta() {}
    public Integer getId_fisioterapeuta() { return id_fisioterapeuta; }
    public void setId_fisioterapeuta(Integer valor) { this.id_fisioterapeuta = valor; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario valor) { this.usuario = valor; }
    public String getCrefito() { return crefito; }
    public void setCrefito(String valor) { this.crefito = valor; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String valor) { this.especialidade = valor; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String valor) { this.cnpj = valor; }
}
