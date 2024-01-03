package br.ufac.sgcmapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufac.sgcmapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(
        "SELECT p FROM Usuario p WHERE p.nomeCompleto LIKE %?1%" +
        " OR p.nomeUsuario LIKE %?1%"
    )
    Page<Usuario> busca(String termoBusca, Pageable page);

    Usuario findByNomeUsuario(String nomeUsuario);
    
}
