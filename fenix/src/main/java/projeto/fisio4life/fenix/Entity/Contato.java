package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_contato;
    private String telefone;
    private String celular;
    private String email;

    public Contato() {
    }



    public Integer getId_contato() {
        return id_contato;
    }

    public void setId_contato(Integer id_contato) {
        this.id_contato = id_contato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
