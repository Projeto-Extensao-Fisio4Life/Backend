package projeto.fisio4life.fenix.Dto.documentoProntuarioDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DocumentoProntuarioResponseDto {

    private Integer idDocumento;

    private Integer idProntuario;

    private String nomeArquivo;

    private String tipoDocumento;

    private String s3Key;

    private String urlDocumento;

    private LocalDateTime dataUpload;

    public DocumentoProntuarioResponseDto() {}

    public Integer getIdDocumento() { return idDocumento; }
    public void setIdDocumento(Integer valor) { this.idDocumento = valor; }

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
