package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// --- ENTITY ---
@Entity
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_exercicio;
    private String categoria;
    private String nome;
    private String url_exercicio;

    public Exercicio() {
    }

    public Exercicio(Long id_exercicio, String categoria, String nome, String url_exercicio) {
        this.id_exercicio = id_exercicio;
        this.categoria = categoria;
        this.nome = nome;
        this.url_exercicio = url_exercicio;
    }

    public Long getId_exercicio() {
        return id_exercicio;
    }

    public void setId_exercicio(Long id_exercicio) {
        this.id_exercicio = id_exercicio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl_exercicio() {
        return url_exercicio;
    }

    public void setUrl_exercicio(String url_exercicio) {
        this.url_exercicio = url_exercicio;
    }
}