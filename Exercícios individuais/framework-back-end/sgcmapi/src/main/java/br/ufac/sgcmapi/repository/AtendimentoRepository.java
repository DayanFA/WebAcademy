package br.ufac.sgcmapi.repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufac.sgcmapi.model.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    @Query("SELECT a FROM Atendimento a" +
        " LEFT JOIN Profissional p ON p.id = a.profissional" +
        " LEFT JOIN Paciente pa ON pa.id = a.paciente" +
        " LEFT JOIN Convenio c ON c.id = a.convenio" +
        " LEFT JOIN Unidade u ON u.id = p.unidade" +
        " LEFT JOIN Especialidade e ON e.id = p.especialidade" +
        " WHERE p.nome LIKE %?1%" +
        " OR pa.nome LIKE %?1%" +
        " OR c.nome LIKE %?1%" +
        " OR u.nome LIKE %?1%" +
        " OR e.nome LIKE %?1%")
    List<Atendimento> busca(String termoBusca);

    @Query("SELECT a.hora FROM Atendimento a" +
        " WHERE a.profissional.id = :id" +
        " AND a.data = :data")
    List<LocalTime> buscaProDia(Long id, LocalDate data);
    
}
