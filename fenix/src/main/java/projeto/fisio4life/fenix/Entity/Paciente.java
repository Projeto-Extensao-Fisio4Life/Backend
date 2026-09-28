package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Entity
@Table
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_paciente;
    @OneToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
    private Date data_nascimento;
    @Column(unique = true)
    private String cpf;
    private Boolean permite_atendimento_grupo;

    public Paciente() {
    }

    public Paciente(Integer id_paciente, Usuario usuario, Date data_nascimento, String cpf, Boolean permite_atendimento_grupo) {
        this.id_paciente = id_paciente;
        this.usuario = usuario;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.permite_atendimento_grupo = permite_atendimento_grupo;
    }

    public Integer getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Integer id_paciente) {
        this.id_paciente = id_paciente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getPermite_atendimento_grupo() {
        return permite_atendimento_grupo;
    }

    public void setPermite_atendimento_grupo(Boolean permite_atendimento_grupo) {
        this.permite_atendimento_grupo = permite_atendimento_grupo;
    }
}
