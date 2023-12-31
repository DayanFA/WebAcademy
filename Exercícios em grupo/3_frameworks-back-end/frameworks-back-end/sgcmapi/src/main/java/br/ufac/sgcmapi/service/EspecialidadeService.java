package br.ufac.sgcmapi.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Especialidade;
import br.ufac.sgcmapi.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService implements IService<Especialidade> {

    @Autowired
    private EspecialidadeRepository repo;

    @Override
    public List<Especialidade> get() {
        return repo.findAll();
    }

    @Override
    public Especialidade get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Especialidade> get(String termoBusca) {
        return repo.busca(termoBusca);
    }

    @Override
    public Especialidade save(Especialidade objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Especialidade> getByPapel(String papel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByPapel'");
    }

    @Override
    public List<Especialidade> getByNomeCompleto(String nc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByNomeCompleto'");
    }

    @Override
    public List<Especialidade> getAtivos(boolean ativo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAtivos'");
    }

}
