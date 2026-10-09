package projeto.fisio4life.fenix.Dto.fisioterapeutaDto;

public class FisioterapeutaResponseDto {

    private Integer idFisioterapeuta;
    private Integer idUsuario;
    private String crefito;
    private String especialidade;
    private String cnpj;

    public FisioterapeutaResponseDto() {
    }

    public FisioterapeutaResponseDto(Integer idFisioterapeuta, Integer idUsuario, String crefito, String especialidade, String cnpj) {
        this.idFisioterapeuta = idFisioterapeuta;
        this.idUsuario = idUsuario;
        this.crefito = crefito;
        this.especialidade = especialidade;
        this.cnpj = cnpj;
    }

    public Integer getIdFisioterapeuta() {
        return idFisioterapeuta;
    }

    public void setIdFisioterapeuta(Integer idFisioterapeuta) {
        this.idFisioterapeuta = idFisioterapeuta;
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
