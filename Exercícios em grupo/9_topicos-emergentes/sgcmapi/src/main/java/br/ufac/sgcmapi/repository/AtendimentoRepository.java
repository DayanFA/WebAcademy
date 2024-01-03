package br.ufac.sgcmapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.model.Profissional;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    @Query("SELECT a FROM Atendimento a" +
    " LEFT JOIN Profissional p ON p.id = a.profissional" +
    " LEFT JOIN Paciente pa ON pa.id = a.paciente" +
    " LEFT JOIN Convenio c ON c.id = a.convenio" +
    " LEFT JOIN Unidade u ON u.id = p.unidade" +
    " LEFT JOIN Especialidade e ON e.id = p.especialidade" +
    " WHERE (p.nome LIKE %?1%" +
    " OR pa.nome LIKE %?1%" +
    " OR c.nome LIKE %?1%" +
    " OR u.nome LIKE %?1%" +
    " OR e.nome LIKE %?1%)" +
    " AND (a.status IN ?2 OR ?2 IS NULL)" +
    " AND (a.data >= ?3 OR ?3 IS NULL)")
    Page<Atendimento> busca(String termoBusca, List<EStatus> status, LocalDate data, Pageable page);


    List<Atendimento> findByProfissionalAndDataAndStatusNot(
        Profissional profissional, LocalDate data, EStatus status);

    Page<Atendimento> findByStatusInAndDataGreaterThanEqual(List<EStatus> status, LocalDate data, Pageable page);

    
}
