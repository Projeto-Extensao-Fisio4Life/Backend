package projeto.fisio4life.fenix.Dto.documentoProntuarioDto;

import java.time.LocalDateTime;

public class DocumentoProntuarioResponseDto {

    private Integer idDocumento;
    private Integer idProntuario;
    private String nomeArquivo;
    private String tipoDocumento;
    private String s3Key;
    private String urlDocumento;
    private LocalDateTime dataUpload;
}
