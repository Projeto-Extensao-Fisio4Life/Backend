package projeto.fisio4life.fenix.Dto.documentoProntuarioDto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class DocumentoProntuarioRequestDto {

    @NotNull
    private Integer idProntuario;

    @NotBlank
    @Size(max = 45)
    private String nomeArquivo;

    @NotBlank
    @Size(max = 45)
    private String tipoDocumento;

    @NotBlank
    @Size(max = 45)
    private String s3Key;

    @NotBlank
    @Size(max = 45)
    private String urlDocumento;

    @NotNull
    private LocalDateTime dataUpload;

    public DocumentoProntuarioRequestDto() {}

    public Integer getIdProntuario() { return idProntuario; }
    public void setIdProntuario(Integer valor) { this.idProntuario = valor; }

    public String getNomeArquivo() { return nomeArquivo; }
    public void setNomeArquivo(String valor) { this.nomeArquivo = valor; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String valor) { this.tipoDocumento = valor; }

    public String getS3Key() { return s3Key; }
    public void setS3Key(String valor) { this.s3Key = valor; }

    public String getUrlDocumento() { return urlDocumento; }
    public void setUrlDocumento(String valor) { this.urlDocumento = valor; }

    public LocalDateTime getDataUpload() { return dataUpload; }
    public void setDataUpload(LocalDateTime valor) { this.dataUpload = valor; }

}
