package projeto.fisio4life.fenix.Mapper;

import projeto.fisio4life.fenix.Dto.UsuarioDtoTESTED.UsuarioRequestDto;
import projeto.fisio4life.fenix.Dto.UsuarioDtoTESTED.UsuarioResponseDto;
import projeto.fisio4life.fenix.Dto.UsuarioDtoTESTED.UsuarioResumedDto;
import projeto.fisio4life.fenix.Entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static UsuarioResponseDto toResponseDto(Usuario usuario){
        UsuarioResponseDto dto = new UsuarioResponseDto();

        dto.setId_usuario(dto.getId_usuario());
        dto.setEndereco(dto.getEndereco());
        dto.setContato(dto.getContato());
        dto.setNome(dto.getNome());
        dto.setTipo_usuario(dto.getTipo_usuario());
        dto.setData_cadastro(dto.getData_cadastro());

        return dto;
    }

    public static List<UsuarioResponseDto> toResponseDto(List<Usuario> usuarios){
        List<UsuarioResponseDto> usuariosMapeados = new ArrayList<>();

        for (Usuario usuario_DaVez: usuarios){

            UsuarioResponseDto dto = UsuarioMapper.toResponseDto(usuario_DaVez);
            usuariosMapeados.add(dto);
        }
        return usuariosMapeados;
    }

    public static UsuarioResumedDto toResumedDto(Usuario usuario){
        UsuarioResumedDto dto = new UsuarioResumedDto();

        dto.setId_usuario(usuario.getId_usuario());
        dto.setNome(usuario.getNome());
        dto.setTipo_usuario(usuario.getTipo_usuario());
        dto.setStatus_usuario(usuario.getStatus_usuario());

        return dto;
    }

    public static List<UsuarioResumedDto> toResumedDto(List<Usuario> usuarios){
        List<UsuarioResumedDto> usuariosMapeados = new ArrayList<>();

        for (Usuario usuario_DaVez: usuarios){

            UsuarioResumedDto dto = UsuarioMapper.toResumedDto(usuario_DaVez);
            usuariosMapeados.add(dto);
        }
        return usuariosMapeados;
    }

    public static Usuario toEntity(UsuarioRequestDto dto){
        Usuario usuario = new Usuario(
                null,
                null,
                null,
                dto.getNome(),
                dto.getSenha(),
                dto.getTipo_usuario(),
                dto.getData_cadastro(),
                null
        );
      return usuario;
    }


}
