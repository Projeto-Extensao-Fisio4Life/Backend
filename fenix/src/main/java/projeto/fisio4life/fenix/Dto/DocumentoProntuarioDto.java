package projeto.fisio4life.fenix.Dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import java.time.LocalDateTime;

public class DocumentoProntuarioDto {

    public record Request(
            @NotNull
            Long prontuarioId,
            @NotBlank
            String nomeArquivo,
            @NotBlank
            String tipoDocumento,
            @NotBlank
            String s3Key,
            @NotBlank
            @URL
            String urlDocumento
    ) {}

    public record Response(
            Long id,
            String nomeArquivo,
            String urlDocumento,
            LocalDateTime dataUpload
    ) {}
}