package projeto.fisio4life.fenix.Dto.evolucaoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class EvolucaoRequestDto {

    @NotNull
    private Integer idSessao;

    @NotNull
    private Integer idProntuario;

    @NotNull
    private LocalDateTime dataEvolucao;

    @NotBlank
    @Size(max = 255)
    private String conduta;

    @NotBlank
    @Size(max = 255)
    private String avaliacao;

    @Size(max = 255)
    private String observacao;
}
