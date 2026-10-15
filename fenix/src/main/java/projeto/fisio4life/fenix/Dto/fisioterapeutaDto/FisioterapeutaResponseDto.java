package projeto.fisio4life.fenix.Dto.fisioterapeutaDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FisioterapeutaResponseDto {

    private Integer idFisioterapeuta;

    private Integer idUsuario;

    private String crefito;

    private String especialidade;

    private String cnpj;

    public FisioterapeutaResponseDto() {}

    public Integer getIdFisioterapeuta() { return idFisioterapeuta; }
    public void setIdFisioterapeuta(Integer valor) { this.idFisioterapeuta = valor; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer valor) { this.idUsuario = valor; }

    public String getCrefito() { return crefito; }
    public void setCrefito(String valor) { this.crefito = valor; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String valor) { this.especialidade = valor; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String valor) { this.cnpj = valor; }

}
