package projeto.fisio4life.fenix.Dto.sessaoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class SessaoRequestDto {

    @NotNull
    private Integer idAgendamento;

    @NotNull
    private LocalDateTime dataSessao;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotBlank
    @Size(max = 255)
    private String observacao;
}
