package br.ufac.sgcmapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufac.sgcmapi.model.Convenio;

public interface ConvenioRepository extends JpaRepository<Convenio, Long> {

    @Query("SELECT c FROM Convenio c" +
        " WHERE c.nome LIKE %?1%" +
        " OR c.razaoSocial LIKE %?1%" +
        " OR c.cnpj LIKE %?1%" +
        " OR c.representante LIKE %?1%" +
        " OR c.email LIKE %?1%" +
        " OR c.telefone LIKE %?1%")
    Page<Convenio> busca(String termoBusca, Pageable page);
    
}
