package projeto.fisio4life.fenix.Dto.clinicaDto;

import jakarta.validation.constraints.*;

public class ClinicaRequestDto {

    @NotNull
    private Integer idContato;

    @NotNull
    private Integer idEndereco;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{14}")
    @Size(min = 14, max = 14)
    private String cnpj;

    public ClinicaRequestDto() {}

    public Integer getIdContato() { return idContato; }
    public void setIdContato(Integer valor) { this.idContato = valor; }

    public Integer getIdEndereco() { return idEndereco; }
    public void setIdEndereco(Integer valor) { this.idEndereco = valor; }

    public String getNome() { return nome; }
    public void setNome(String valor) { this.nome = valor; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String valor) { this.cnpj = valor; }

}
