package projeto.fisio4life.fenix.Dto.escalaDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EscalaResponseDto {

    private Integer idEscala;

    private String nome;

    private String descricao;

    public EscalaResponseDto() {}

    public Integer getIdEscala() { return idEscala; }
    public void setIdEscala(Integer valor) { this.idEscala = valor; }

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String valor) { this.descricao = valor; }

}
