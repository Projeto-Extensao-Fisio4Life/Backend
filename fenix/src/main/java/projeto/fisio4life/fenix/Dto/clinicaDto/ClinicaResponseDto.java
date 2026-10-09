package projeto.fisio4life.fenix.Dto.clinicaDto;

public class ClinicaResponseDto {

    private Integer idClinica;
    private Integer idContato;
    private Integer idEndereco;
    private String nome;
    private String cnpj;

    public ClinicaResponseDto() {
    }

    public ClinicaResponseDto(Integer idClinica, Integer idContato, Integer idEndereco, String nome, String cnpj) {
        this.idClinica = idClinica;
        this.idContato = idContato;
        this.idEndereco = idEndereco;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Integer getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Integer idClinica) {
        this.idClinica = idClinica;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
