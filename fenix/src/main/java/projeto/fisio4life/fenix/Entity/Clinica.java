package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clinica")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clinica", nullable = false)
    private Integer id_clinica;

    @ManyToOne
    @JoinColumn(name = "fk_contato", nullable = false)
    private Contato contato;

    @OneToOne
    @JoinColumn(name = "fk_endereco", nullable = false, unique = true)
    private Endereco endereco;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    public Clinica() {}
    public Integer getId_clinica() { return id_clinica; }
    public void setId_clinica(Integer valor) { this.id_clinica = valor; }
    public Contato getContato() { return contato; }
    public void setContato(Contato valor) { this.contato = valor; }
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco valor) { this.endereco = valor; }
    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String valor) { this.cnpj = valor; }
}
