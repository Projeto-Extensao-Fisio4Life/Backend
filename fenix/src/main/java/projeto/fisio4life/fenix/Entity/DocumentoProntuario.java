package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documento_prontuario")
public class DocumentoProntuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento", nullable = false)
    private Integer id_documento;

    @ManyToOne
    @JoinColumn(name = "fk_prontuario", nullable = false)
    private Prontuario prontuario;

    @Column(name = "nome_arquivo", nullable = false, length = 45)
    private String nome_arquivo;

    @Column(name = "tipo_documento", nullable = false, length = 45)
    private String tipo_documento;

    @Column(name = "s3_key", nullable = false, length = 45)
    private String s3_key;

    @Column(name = "url_documento", nullable = false, length = 45)
    private String url_documento;

    @Column(name = "data_upload", nullable = false)
    private LocalDateTime data_upload;

    public DocumentoProntuario() {}
    public Integer getId_documento() { return id_documento; }
    public void setId_documento(Integer valor) { this.id_documento = valor; }
    public Prontuario getProntuario() { return prontuario; }
    public void setProntuario(Prontuario valor) { this.prontuario = valor; }
    public String getNome_arquivo() { return nome_arquivo; }
    public void setNome_arquivo(String valor) { this.nome_arquivo = valor; }
    public String getTipo_documento() { return tipo_documento; }
    public void setTipo_documento(String valor) { this.tipo_documento = valor; }
    public String getS3_key() { return s3_key; }
    public void setS3_key(String valor) { this.s3_key = valor; }
    public String getUrl_documento() { return url_documento; }
    public void setUrl_documento(String valor) { this.url_documento = valor; }
    public LocalDateTime getData_upload() { return data_upload; }
    public void setData_upload(LocalDateTime valor) { this.data_upload = valor; }
}
