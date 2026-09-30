package projeto.fisio4life.fenix.Dto.servicoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ServicoRequestDto {

    @NotNull
    private Integer idClinica;

    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotNull
    private Integer quantidadeSessoes;

    @NotNull
    private BigDecimal valor;

    @NotBlank
    @Size(max = 20)
    private String tipo;
}
