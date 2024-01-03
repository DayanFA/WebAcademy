package br.ufac.sgcmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.repository.ConvenioRepository;

@Service
public class ConvenioService implements IService<Convenio> {

    @Autowired
    private ConvenioRepository repo;

    @Override
    public Page<Convenio> get(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public Convenio get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<Convenio> get(String termoBusca, Pageable page) {
        return repo.busca(termoBusca, page);
    }

    @Override
    public Convenio save(Convenio objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
