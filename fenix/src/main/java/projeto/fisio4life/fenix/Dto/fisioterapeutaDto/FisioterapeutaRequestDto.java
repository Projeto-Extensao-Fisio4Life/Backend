package projeto.fisio4life.fenix.Dto.fisioterapeutaDto;

import jakarta.validation.constraints.*;

public class FisioterapeutaRequestDto {

    @NotNull
    private Integer idUsuario;

    @NotBlank
    @Size(max = 10)
    private String crefito;

    @NotBlank
    @Size(max = 45)
    private String especialidade;

    @NotBlank
    @Pattern(regexp = "\\d{14}")
    @Size(min = 14, max = 14)
    private String cnpj;

    public FisioterapeutaRequestDto() {}

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer valor) { this.idUsuario = valor; }

    public String getCrefito() { return crefito; }
    public void setCrefito(String valor) { this.crefito = valor; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String valor) { this.especialidade = valor; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String valor) { this.cnpj = valor; }

}
