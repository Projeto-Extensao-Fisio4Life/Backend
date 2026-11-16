package projeto.fisio4life.fenix.Interface;

import projeto.fisio4life.fenix.Dto.authDto.AuthRequestDto;
import projeto.fisio4life.fenix.Dto.authDto.AuthResponseDto;
import projeto.fisio4life.fenix.Entity.Usuario;

public interface LoginStrategy {
    AuthResponseDto login(AuthRequestDto dto);
}
