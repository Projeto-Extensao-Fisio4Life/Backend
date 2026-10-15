package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", nullable = false)
    private Integer id_endereco;

    @Column(name = "logradouro", nullable = false, length = 45)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "bairro", nullable = false, length = 45)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 45)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "cep", nullable = false, length = 45)
    private String cep;

    public Endereco() {}
    public Integer getId_endereco() { return id_endereco; }
    public void setId_endereco(Integer valor) { this.id_endereco = valor; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String valor) { this.logradouro = valor; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer valor) { this.numero = valor; }
    public String getBairro() { return bairro; }
    public void setBairro(String valor) { this.bairro = valor; }
    public String getCidade() { return cidade; }
    public void setCidade(String valor) { this.cidade = valor; }
    public String getEstado() { return estado; }
    public void setEstado(String valor) { this.estado = valor; }
    public String getCep() { return cep; }
    public void setCep(String valor) { this.cep = valor; }
}
