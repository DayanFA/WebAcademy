package br.ufac.sgcmapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufac.sgcmapi.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query(
        "SELECT p FROM Paciente p WHERE p.nome LIKE %?1%" +
        " OR p.email LIKE %?1%" +
        " OR p.telefone LIKE %?1%" +
        " OR p.cep LIKE %?1%" +
        " OR p.endereco LIKE %?1%"
    )
    Page<Paciente> busca(String termoBusca, Pageable page);
    
}
