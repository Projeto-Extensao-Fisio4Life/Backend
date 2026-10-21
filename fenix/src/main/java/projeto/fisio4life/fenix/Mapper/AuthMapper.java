package projeto.fisio4life.fenix.Mapper;

import projeto.fisio4life.fenix.Dto.authDto.AuthFisioterapeutaResponseDto;
import projeto.fisio4life.fenix.Dto.authDto.AuthPacienteResponseDto;
import projeto.fisio4life.fenix.Dto.authDto.AuthResponseDto;
import projeto.fisio4life.fenix.Entity.Fisioterapeuta;
import projeto.fisio4life.fenix.Entity.Paciente;
import projeto.fisio4life.fenix.Entity.Usuario;

public class AuthMapper {


    public static AuthResponseDto toResponse(Usuario usuario, Fisioterapeuta fisioterapeuta, String token) {
        return new AuthFisioterapeutaResponseDto(
                usuario.getId_usuario(),
                usuario.getEndereco().getId_endereco(),
                usuario.getContato().getId_contato(),
                usuario.getNome(),
                usuario.getData_nascimento(),
                usuario.getTipo_usuario(),
                usuario.getData_cadastro(),
                usuario.getStatus_usuario(),
                token,
                fisioterapeuta.getId_fisioterapeuta(),
                fisioterapeuta.getCrefito(),
                fisioterapeuta.getEspecialidade(),
                fisioterapeuta.getCnpj()
        );

    }
    public static AuthResponseDto toResponse(Usuario usuario, String token) {
        return new AuthResponseDto(
                usuario.getId_usuario(),
                usuario.getEndereco().getId_endereco(),
                usuario.getContato().getId_contato(),
                usuario.getNome(),
                usuario.getData_nascimento(),
                usuario.getTipo_usuario(),
                usuario.getData_cadastro(),
                usuario.getStatus_usuario(),
                token
        );

    }

    public static AuthResponseDto toResponse(Usuario usuario, Paciente paciente, String token) {
        return new AuthPacienteResponseDto(
                usuario.getId_usuario(),
                usuario.getEndereco().getId_endereco(),
                usuario.getContato().getId_contato(),
                usuario.getNome(),
                usuario.getData_nascimento(),
                usuario.getTipo_usuario(),
                usuario.getData_cadastro(),
                usuario.getStatus_usuario(),
                token,
                paciente.getId_paciente(),
                paciente.getCpf(),
                paciente.getPermite_atendimento_grupo()
        );

    }


}
