package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.repository.PacienteRepository;

@Service
public class PacienteService implements IService<Paciente> {

    @Autowired
    private PacienteRepository repo;

    @Override
    public List<Paciente> get() {
        return repo.findAll();
    }

    @Override
    public Paciente get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Paciente> get(String termoBusca) {
        return repo.busca(termoBusca);
    }

    @Override
    public Paciente save(Paciente objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
