package br.ufac.sgcmapi.controller.dto;

import br.ufac.sgcmapi.model.Usuario;

public record UsuarioDto(
    Long id,
    String nomeCompleto,
    String nomeUsuario,
    String papel,
    boolean ativo
) {

    public static UsuarioDto fromEntity(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto(
            usuario.getId(),
            usuario.getNomeCompleto(),
            usuario.getNomeUsuario(),
            usuario.getPapel().toString(),
            usuario.isAtivo()
        );
        return dto;
    }
    
}
