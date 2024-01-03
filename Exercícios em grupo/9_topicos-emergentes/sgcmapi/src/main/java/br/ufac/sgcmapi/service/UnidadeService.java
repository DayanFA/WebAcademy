package br.ufac.sgcmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.repository.UnidadeRepository;

@Service
public class UnidadeService implements IService<Unidade> {

    @Autowired
    private UnidadeRepository repo;

    @Override
    public Page<Unidade> get(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public Unidade get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<Unidade> get(String termoBusca, Pageable page) {
        return repo.busca(termoBusca, page);
    }

    @Override
    public Unidade save(Unidade objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);        
    }
    
}
