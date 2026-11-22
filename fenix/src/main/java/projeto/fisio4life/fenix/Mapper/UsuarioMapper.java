package projeto.fisio4life.fenix.Mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioRequestDto;
import projeto.fisio4life.fenix.Dto.usuarioDto.UsuarioResponseDto;
import projeto.fisio4life.fenix.Entity.Endereco;
import projeto.fisio4life.fenix.Entity.Usuario;
import projeto.fisio4life.fenix.Enum.StatusUsuarioEnum;
import projeto.fisio4life.fenix.Enum.TipoUsuarioEnum;
import projeto.fisio4life.fenix.Exception.TipoUsuarioNaoEncontrado;

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
        entity.setNome(dto.getNome());
        entity.setData_nascimento(dto.getDataNascimento());
        entity.setEmail(dto.getEmail());
        entity.setCelular(dto.getCelular());
        entity.setTelefone(dto.getTelefone());
        entity.setSenha(dto.getSenha());
        TipoUsuarioEnum tipoUsuario = null;
        for (TipoUsuarioEnum tipoUsuarioEnum : TipoUsuarioEnum.values()){

            if (Objects.equals(tipoUsuarioEnum.toString(), dto.getTipoUsuario())) {
                tipoUsuario = tipoUsuarioEnum;
                break;
            }
        }

        if (tipoUsuario == null) {
            throw new TipoUsuarioNaoEncontrado("Tipo de usuário não encontrado: " + dto.getTipoUsuario());
        }
        entity.setTipo_usuario(tipoUsuario);
        entity.setStatus_usuario(dto.getStatusUsuario());

        return entity;
    }

    public static UsuarioResponseDto toResponseDto(Usuario entity) {
        if (entity == null) return null;

        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setIdUsuario(entity.getId_usuario());
        if (entity.getEndereco() != null) dto.setIdEndereco(entity.getEndereco().getId_endereco());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCelular(entity.getCelular());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getData_nascimento());
        dto.setTipoUsuario(entity.getTipo_usuario());
        dto.setDataCadastro(entity.getData_cadastro());
        dto.setStatusUsuario(entity.getStatus_usuario());
        return dto;
    }

    public static List<UsuarioResponseDto> toResponseDto(List<Usuario> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream().map(UsuarioMapper::toResponseDto).collect(Collectors.toList());
    }
}
