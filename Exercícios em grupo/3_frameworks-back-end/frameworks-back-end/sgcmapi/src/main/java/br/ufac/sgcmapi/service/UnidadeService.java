package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.repository.UnidadeRepository;

@Service
public class UnidadeService implements IService<Unidade> {

    @Autowired
    private UnidadeRepository repo;

    @Override
    public List<Unidade> get() {
        return repo.findAll();
    }

    @Override
    public Unidade get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Unidade> get(String termoBusca) {
        return repo.findByEndereco(termoBusca);
    }

    @Override
    public List<Unidade> getByNomeCompleto(String nc) {
        return repo.findByNome(nc);
    }

    @Override
    public Unidade save(Unidade objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        Unidade unidade = this.get(id);
        if (unidade != null) {
            repo.delete(unidade);
        }
    }

    @Override
    public List<Unidade> getByPapel(String papel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByPapel'");
    }

    @Override
    public List<Unidade> getAtivos(boolean ativo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAtivos'");
    }
}
