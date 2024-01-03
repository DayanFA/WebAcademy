package br.ufac.sgcmapi.service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

@Service
public class AtendimentoService implements IService<Atendimento> {

    @Autowired
    private AtendimentoRepository repo;

    @Override
    public List<Atendimento> get() {
        return repo.findAll();
    }

    @Override
    public Atendimento get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Atendimento> get(String termoBusca) {
        return repo.busca(termoBusca);
    }

    @Override
    public Atendimento save(Atendimento objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        Atendimento registro = this.get(id);
        registro.setStatus(EStatus.CANCELADO);
        repo.save(registro);
    }

    public Atendimento updateStatus(Long id) {
        Atendimento registro = this.get(id);
        registro.setStatus(registro.getStatus().proximo());
        repo.save(registro);
        return registro;
    }

    @Override
    public List<String> get(Long id, LocalDate data) {
        List<LocalTime> horAgen = repo.buscaProDia(id, data);
        List<String> horAgenStr = new ArrayList<>();
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("HH:mm:ss");
        for (LocalTime horario : horAgen) {
            String horarioF = horario.format(formatar);
            horAgenStr.add(horarioF);
            //horAgenStr.add(horario.toString());
        }
        return horAgenStr;
    }
    
}
