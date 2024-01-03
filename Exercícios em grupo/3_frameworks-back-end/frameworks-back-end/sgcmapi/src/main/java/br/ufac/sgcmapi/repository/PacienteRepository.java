package br.ufac.sgcmapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufac.sgcmapi.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT pa FROM Paciente pa" +
        " WHERE pa.nome LIKE %?1%" +
        " OR pa.nome LIKE %?1%" +
        " OR pa.email LIKE %?1%" +
        " OR pa.telefone LIKE %?1%" +
        " OR pa.dataNascimento LIKE %?1%" +
        " OR pa.grupoSanguineo LIKE %?1%" +
        " OR pa.sexo LIKE %?1%" +
        " OR pa.cep LIKE %?1%" +
        " OR pa.endereco LIKE %?1%" +
        " OR pa.estado LIKE %?1%" +
        " OR pa.cidade LIKE %?1%" )
    List<Paciente> busca(String termoBusca);
    
}
