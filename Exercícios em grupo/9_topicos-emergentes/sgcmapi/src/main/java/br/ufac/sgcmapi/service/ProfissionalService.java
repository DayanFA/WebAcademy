package br.ufac.sgcmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.repository.ProfissionalRepository;

@Service
public class ProfissionalService implements IService<Profissional> {

    @Autowired
    private ProfissionalRepository repo;

    @Override
    public Page<Profissional> get(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public Profissional get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<Profissional> get(String termoBusca, Pageable page) {
        return repo.busca(termoBusca, page);
    }

    @Override
    public Profissional save(Profissional objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
