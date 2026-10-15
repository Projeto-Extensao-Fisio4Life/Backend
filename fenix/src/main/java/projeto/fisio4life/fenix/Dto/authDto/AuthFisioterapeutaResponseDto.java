package projeto.fisio4life.fenix.Dto.authDto;

import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AuthFisioterapeutaResponseDto extends AuthResponseDto {

    private Integer idFisioterapeuta;
    private String crefito;
    private String especialidade;
    private String cnpj;

    public AuthFisioterapeutaResponseDto(Integer idUsuario, Integer idEndereco, Integer idContato, String nome, LocalDate dataNascimento, TipoUsuarioEnum tipoUsuario, LocalDateTime dataCadastro, Integer statusUsuario, long tokenJwt, Integer idFisioterapeuta, String crefito, String especialidade, String cnpj) {
        super(idUsuario, idEndereco, idContato, nome, dataNascimento, tipoUsuario, dataCadastro, statusUsuario, tokenJwt);
        this.idFisioterapeuta = idFisioterapeuta;
        this.crefito = crefito;
        this.especialidade = especialidade;
        this.cnpj = cnpj;
    }

    public AuthFisioterapeutaResponseDto(Integer idUsuario, Integer idEndereco, Integer idContato, String nome, LocalDate dataNascimento, String tipoUsuario, LocalDateTime dataCadastro, Integer statusUsuario, long tokenJwt) {
        super(idUsuario, idEndereco, idContato, nome, dataNascimento, tipoUsuario, dataCadastro, statusUsuario, tokenJwt);
    }

    public Integer getIdFisioterapeuta() {
        return idFisioterapeuta;
    }

    public void setIdFisioterapeuta(Integer idFisioterapeuta) {
        this.idFisioterapeuta = idFisioterapeuta;
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
