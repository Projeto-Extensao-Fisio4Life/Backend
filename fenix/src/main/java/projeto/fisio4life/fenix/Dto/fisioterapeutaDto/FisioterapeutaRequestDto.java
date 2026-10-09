package projeto.fisio4life.fenix.Dto.fisioterapeutaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    @Size(max = 14)
    private String cnpj;

    public FisioterapeutaRequestDto() {
    }

    public FisioterapeutaRequestDto(Integer idUsuario, String crefito, String especialidade, String cnpj) {
        this.idUsuario = idUsuario;
        this.crefito = crefito;
        this.especialidade = especialidade;
        this.cnpj = cnpj;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCrefito() {
        return crefito;
    }

    public void setCrefito(String crefito) {
        this.crefito = crefito;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
