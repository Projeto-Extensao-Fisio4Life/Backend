package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Table
public class Clinica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_clinica;
    @OneToOne
    @JoinColumn(name = "fk_contato")
    private Contato contato;
    @OneToOne
    @JoinColumn(name = "fk_endereco")
    private Endereco endereco;
    private String nome;
    @Column(unique = true, length = 14)
    private String cnpj;

    public Clinica() {
    }

    public Clinica(Long id_clinica, Contato contato, Endereco endereco, String nome, String cnpj) {
        this.id_clinica = id_clinica;
        this.contato = contato;
        this.endereco = endereco;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Long getId_clinica() {
        return id_clinica;
    }

    public void setId_clinica(Long id_clinica) {
        this.id_clinica = id_clinica;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
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
}
