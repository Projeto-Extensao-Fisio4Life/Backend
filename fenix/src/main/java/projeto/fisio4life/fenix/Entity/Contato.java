package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contato", nullable = false)
    private Integer id_contato;

    @Column(name = "telefone", nullable = false, length = 14)
    private String telefone;

    @Column(name = "celular", nullable = false, length = 14)
    private String celular;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    public Contato() {}
    public Integer getId_contato() { return id_contato; }
    public void setId_contato(Integer valor) { this.id_contato = valor; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String valor) { this.telefone = valor; }
    public String getCelular() { return celular; }
    public void setCelular(String valor) { this.celular = valor; }
    public String getEmail() { return email; }
    public void setEmail(String valor) { this.email = valor; }
}
