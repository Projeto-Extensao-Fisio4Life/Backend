package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente", nullable = false)
    private Integer id_paciente;

    @OneToOne
    @JoinColumn(name = "fk_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Convert(converter = BooleanToTinyIntConverter.class)
    @Column(name = "permite_atendimento_grupo", nullable = false)
    private Boolean permite_atendimento_grupo;

    public Paciente() {}
    public Integer getId_paciente() { return id_paciente; }
    public void setId_paciente(Integer valor) { this.id_paciente = valor; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario valor) { this.usuario = valor; }
    public String getCpf() { return cpf; }
    public void setCpf(String valor) { this.cpf = valor; }
    public Boolean getPermite_atendimento_grupo() { return permite_atendimento_grupo; }
    public void setPermite_atendimento_grupo(Boolean valor) { this.permite_atendimento_grupo = valor; }
}
