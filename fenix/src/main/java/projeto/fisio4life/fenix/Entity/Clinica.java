package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clinica")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clinica", nullable = false)
    private Integer id_clinica;

    @OneToOne
    @JoinColumn(name = "fk_endereco", nullable = false, unique = true)
    private Endereco endereco;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    @Column(name = "telefone",  length = 14)
    private String telefone;

    @Column(name = "celular", nullable = false, length = 14)
    private String celular;

    @Column(name = "email", nullable = false, length = 45)
    private String email;


    public Clinica() {}


    public Integer getId_clinica() {
        return id_clinica;
    }

    public void setId_clinica(Integer id_clinica) {
        this.id_clinica = id_clinica;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
