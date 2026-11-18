package projeto.fisio4life.fenix.Controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.fisio4life.fenix.Dto.authDto.AuthRequestDto;
import projeto.fisio4life.fenix.Dto.authDto.AuthResponseDto;
import projeto.fisio4life.fenix.Service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("login/{tipo}")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto dto, @PathVariable String tipo){
        AuthResponseDto authResponseDto = authService.login(dto, tipo);
        return ResponseEntity.status(200).body(authResponseDto);
    }

}
