package projeto.fisio4life.fenix.Dto.pacienteClinicaDto;

import java.time.LocalDateTime;

public class PacienteClinicaResponseDto {

    private Integer idVinculo;
    private Integer idClinica;
    private Integer idPaciente;
    private LocalDateTime dataCadastro;
    private LocalDateTime ultimaAtualizacao;
    private Boolean statusPacienteClinica;

    public PacienteClinicaResponseDto() {
    }

    public PacienteClinicaResponseDto(Integer idVinculo, Integer idClinica, Integer idPaciente, LocalDateTime dataCadastro, LocalDateTime ultimaAtualizacao, Boolean statusPacienteClinica) {
        this.idVinculo = idVinculo;
        this.idClinica = idClinica;
        this.idPaciente = idPaciente;
        this.dataCadastro = dataCadastro;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.statusPacienteClinica = statusPacienteClinica;
    }

    public Integer getIdVinculo() {
        return idVinculo;
    }

    public void setIdVinculo(Integer idVinculo) {
        this.idVinculo = idVinculo;
    }

    public Integer getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Integer idClinica) {
        this.idClinica = idClinica;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Boolean getStatusPacienteClinica() {
        return statusPacienteClinica;
    }

    public void setStatusPacienteClinica(Boolean statusPacienteClinica) {
        this.statusPacienteClinica = statusPacienteClinica;
    }
}
