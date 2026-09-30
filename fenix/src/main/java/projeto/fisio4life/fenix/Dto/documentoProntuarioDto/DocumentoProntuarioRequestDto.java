package projeto.fisio4life.fenix.Dto.documentoProntuarioDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class DocumentoProntuarioRequestDto {

    @NotNull
    private Integer idDocumento;

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
}
