package br.ufac.sgcmapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.repository.UsuarioRepository;

@Service
public class UsuarioService implements IService<Usuario> {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public List<Usuario> get() {
        return repo.findAll();
    }

    @Override
    public Usuario get(Long id) {
        return repo.findById(id).orElse(null);
    }
    
    @Override
    public List<Usuario> getByPapel(String papel) {
        return repo.findByPapel(papel);
    }
    
    @Override
    public List<Usuario> getByNomeCompleto(String nc) {
        return repo.findByNomeCompleto(nc);
    }

    @Override
    public List<Usuario> get(String termoBusca) {
        return repo.findByNomeUsuario(termoBusca);
    }

    @Override
    public List<Usuario> getAtivos(boolean ativo) {
        return repo.findByAtivo(ativo);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = this.get(id);
        if (usuario != null) {
            repo.delete(usuario);
        }
    }

}
