package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.repository.ConvenioRepository;

@Service
public class ConvenioService implements IService<Convenio> {

    @Autowired
    private ConvenioRepository repo;

    @Override
    public List<Convenio> get() {
        return repo.findAll();
    }

    @Override
    public Convenio get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Convenio> get(String termoBusca) {
        return repo.busca(termoBusca);
    }

    @Override
    public Convenio save(Convenio objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Convenio> getByPapel(String papel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByPapel'");
    }

    @Override
    public List<Convenio> getByNomeCompleto(String nc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByNomeCompleto'");
    }

    @Override
    public List<Convenio> getAtivos(boolean ativo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAtivos'");
    }
    
}
