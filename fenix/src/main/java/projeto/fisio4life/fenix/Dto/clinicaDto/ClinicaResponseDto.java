package projeto.fisio4life.fenix.Dto.clinicaDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ClinicaResponseDto {

    private Integer idClinica;

    private Integer idContato;

    private Integer idEndereco;

    private String nome;

    private String cnpj;

    public ClinicaResponseDto() {}

    public Integer getIdClinica() { return idClinica; }
    public void setIdClinica(Integer valor) { this.idClinica = valor; }

    public Integer getIdContato() { return idContato; }
    public void setIdContato(Integer valor) { this.idContato = valor; }

    public Integer getIdEndereco() { return idEndereco; }
    public void setIdEndereco(Integer valor) { this.idEndereco = valor; }

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String valor) { this.cnpj = valor; }

}
