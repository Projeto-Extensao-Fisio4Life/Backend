package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioRequestDto;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioResponseDto;
import projeto.fisio4life.fenix.Entity.Contato;
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Entity.Usuario;

public final class UsuarioMapper {

    private UsuarioMapper() {}

    public static Usuario toEntity(UsuarioRequestDto dto) {
        if (dto == null) return null;

        Usuario entity = new Usuario();

        if (dto.getIdEndereco() != null) {
            Endereco ref = new Endereco();
            ref.setId_endereco(dto.getIdEndereco());
            entity.setEndereco(ref);
        }
        if (dto.getIdContato() != null) {
            Contato ref = new Contato();
            ref.setId_contato(dto.getIdContato());
            entity.setContato(ref);
        }
        entity.setNome(dto.getNome());
        entity.setData_nascimento(dto.getDataNascimento());
        entity.setSenha(dto.getSenha());
        entity.setTipo_usuario(dto.getTipoUsuario());
        entity.setStatus_usuario(Integer.valueOf(dto.getStatusUsuario() == null ? null : dto.getStatusUsuario().byteValue()));

        return entity;
    }

    public static UsuarioResponseDto toResponseDto(Usuario entity) {
        if (entity == null) return null;

        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setIdUsuario(entity.getId_usuario());
        if (entity.getEndereco() != null) dto.setIdEndereco(entity.getEndereco().getId_endereco());
        if (entity.getContato() != null) dto.setIdContato(entity.getContato().getId_contato());
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getData_nascimento());
        dto.setTipoUsuario(entity.getTipo_usuario());
        dto.setDataCadastro(entity.getData_cadastro());
        dto.setStatusUsuario(entity.getStatus_usuario() == null ? null : entity.getStatus_usuario().intValue());

        return dto;
    }

    public static List<UsuarioResponseDto> toResponseDto(List<Usuario> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(UsuarioMapper::toResponseDto).collect(Collectors.toList());
    }
}
