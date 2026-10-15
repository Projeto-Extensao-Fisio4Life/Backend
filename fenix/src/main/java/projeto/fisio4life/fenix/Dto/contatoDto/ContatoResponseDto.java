package projeto.fisio4life.fenix.Dto.contatoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ContatoResponseDto {

    private Integer idContato;

    private String telefone;

    private String celular;

    private String email;

    public ContatoResponseDto() {}

    public Integer getIdContato() { return idContato; }
    public void setIdContato(Integer valor) { this.idContato = valor; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String valor) { this.telefone = valor; }

    public String getCelular() { return celular; }
    public void setCelular(String valor) { this.celular = valor; }

    public String getEmail() { return email; }
    public void setEmail(String valor) { this.email = valor; }

}
