package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "documento_prontuario")
public class DocumentoProntuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_documento;
    @ManyToOne
    @JoinColumn(name = "fk_prontuario")
    private Prontuario prontuario;
    private String nome_arquivo;
    private String tipo_documento;
    private String s3_key;
    private String url_documento;
    private LocalDateTime data_upload;

    public DocumentoProntuario() {
    }

    public DocumentoProntuario(Long id_documento, Prontuario prontuario, String nome_arquivo, String tipo_documento, String s3_key, String url_documento, LocalDateTime data_upload) {
        this.id_documento = id_documento;
        this.prontuario = prontuario;
        this.nome_arquivo = nome_arquivo;
        this.tipo_documento = tipo_documento;
        this.s3_key = s3_key;
        this.url_documento = url_documento;
        this.data_upload = data_upload;
    }

    public Long getId_documento() {
        return id_documento;
    }

    public void setId_documento(Long id_documento) {
        this.id_documento = id_documento;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public String getNome_arquivo() {
        return nome_arquivo;
    }

    public void setNome_arquivo(String nome_arquivo) {
        this.nome_arquivo = nome_arquivo;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getS3_key() {
        return s3_key;
    }

    public void setS3_key(String s3_key) {
        this.s3_key = s3_key;
    }

    public String getUrl_documento() {
        return url_documento;
    }

    public void setUrl_documento(String url_documento) {
        this.url_documento = url_documento;
    }

    public LocalDateTime getData_upload() {
        return data_upload;
    }

    public void setData_upload(LocalDateTime data_upload) {
        this.data_upload = data_upload;
    }
}
