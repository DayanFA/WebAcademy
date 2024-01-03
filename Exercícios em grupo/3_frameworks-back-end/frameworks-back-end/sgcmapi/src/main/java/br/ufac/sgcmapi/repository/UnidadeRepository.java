package br.ufac.sgcmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufac.sgcmapi.model.Unidade;

import java.util.List;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    @Query("SELECT u FROM Unidade u WHERE LOWER(u.endereco) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Unidade> findByEndereco(String termoBusca);

    @Query("SELECT u FROM Unidade u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Unidade> findByNome(String nomeCompleto);
}
