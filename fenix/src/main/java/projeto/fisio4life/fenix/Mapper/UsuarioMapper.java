package projeto.fisio4life.fenix.Mapper;


import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioRequestDto;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioResponseDto;
import projeto.fisio4life.fenix.Entity.Contato;
import projeto.fisio4life.fenix.Entity.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public final class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static Usuario toEntity(UsuarioRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Usuario entity = new Usuario();

        entity.setNome(dto.getNome());
        entity.setData_nascimento(dto.getDataNascimento());
        entity.setSenha(dto.getSenha());
        entity.setTipo_usuario(dto.getTipoUsuario());
        entity.setData_cadastro(dto.getDataCadastro());
        entity.setStatus_usuario(dto.getStatusUsuario());

        return entity;
    }

    public static UsuarioResponseDto toResponseDto(Usuario entity) {
        if (entity == null) {
            return null;
        }


        UsuarioResponseDto dto = new UsuarioResponseDto();

        dto.setIdUsuario(entity.getId_usuario());

        // Solução provisória, puxar id, mas o objetivo é adicionar uma nova requisição
        dto.setIdContato(entity.getContato().getId_contato());
        dto.setIdEndereco(entity.getEndereco().getId_endereco());


        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getData_nascimento());
        dto.setTipoUsuario(entity.getTipo_usuario());
        dto.setDataCadastro(entity.getData_cadastro());
        dto.setStatusUsuario(entity.getStatus_usuario());

        return dto;
    }

    public static List<UsuarioResponseDto> toResponseDto(List<Usuario> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(UsuarioMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
