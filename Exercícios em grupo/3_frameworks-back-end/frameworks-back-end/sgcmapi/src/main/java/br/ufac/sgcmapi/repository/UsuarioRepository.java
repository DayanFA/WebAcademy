package br.ufac.sgcmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.ufac.sgcmapi.model.Usuario;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.papel = ?1")
    List<Usuario> findByPapel(String papel);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nomeCompleto) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Usuario> findByNomeCompleto(String nc);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nomeUsuario) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Usuario> findByNomeUsuario(String termoBusca);

    @Query("SELECT u FROM Usuario u WHERE u.ativo = ?1")
    List<Usuario> findByAtivo(boolean ativo);
}
