package br.ufac.sgcmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.repository.PacienteRepository;

@Service
public class PacienteService implements IService<Paciente> {

    @Autowired
    private PacienteRepository repo;

    @Override
    public Page<Paciente> get(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public Paciente get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<Paciente> get(String termoBusca, Pageable page) {
        return repo.busca(termoBusca, page);
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
