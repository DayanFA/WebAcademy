package br.ufac.sgcmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Especialidade;
import br.ufac.sgcmapi.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService implements IService<Especialidade> {

    @Autowired
    private EspecialidadeRepository repo;

    @Override
    public Page<Especialidade> get(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public Especialidade get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<Especialidade> get(String termoBusca, Pageable page) {
        return repo.busca(termoBusca, page);
    }

    @Override
    public Especialidade save(Especialidade objeto) {
       return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
