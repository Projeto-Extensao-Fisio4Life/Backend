package projeto.fisio4life.fenix.Mapper;

import projeto.fisio4life.fenix.Dto.UsuarioDtoTESTED.UsuarioRequestDtoTESTE;
import projeto.fisio4life.fenix.Dto.UsuarioDtoTESTED.UsuarioResponseDtoTESTE;
import projeto.fisio4life.fenix.Dto.UsuarioDtoTESTED.UsuarioResumedDtoTESTE;
import projeto.fisio4life.fenix.Entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapperTESTE {

    public static UsuarioResponseDtoTESTE toResponseDto(Usuario usuario){
        UsuarioResponseDtoTESTE dto = new UsuarioResponseDtoTESTE();

        dto.setId_usuario(dto.getId_usuario());
        dto.setEndereco(dto.getEndereco());
        dto.setContato(dto.getContato());
        dto.setNome(dto.getNome());
        dto.setTipo_usuario(dto.getTipo_usuario());
        dto.setData_cadastro(dto.getData_cadastro());

        return dto;
    }

    public static List<UsuarioResponseDtoTESTE> toResponseDto(List<Usuario> usuarios){
        List<UsuarioResponseDtoTESTE> usuariosMapeados = new ArrayList<>();

        for (Usuario usuario_DaVez: usuarios){

            UsuarioResponseDtoTESTE dto = UsuarioMapperTESTE.toResponseDto(usuario_DaVez);
            usuariosMapeados.add(dto);
        }
        return usuariosMapeados;
    }

    public static UsuarioResumedDtoTESTE toResumedDto(Usuario usuario){
        UsuarioResumedDtoTESTE dto = new UsuarioResumedDtoTESTE();

        dto.setId_usuario(usuario.getId_usuario());
        dto.setNome(usuario.getNome());
        dto.setTipo_usuario(usuario.getTipo_usuario());
        dto.setStatus_usuario(usuario.getStatus_usuario());

        return dto;
    }

    public static List<UsuarioResumedDtoTESTE> toResumedDto(List<Usuario> usuarios){
        List<UsuarioResumedDtoTESTE> usuariosMapeados = new ArrayList<>();

        for (Usuario usuario_DaVez: usuarios){

            UsuarioResumedDtoTESTE dto = UsuarioMapperTESTE.toResumedDto(usuario_DaVez);
            usuariosMapeados.add(dto);
        }
        return usuariosMapeados;
    }

    public static Usuario toEntity(UsuarioRequestDtoTESTE dto){
        Usuario usuario = new Usuario(
                null,
                null,
                null,
                dto.getNome(),
                dto.getSenha(),
                dto.getTipo_usuario(),
                dto.getData_nascimento(),
                dto.getData_cadastro(),
                null
        );
      return usuario;
    }


}
