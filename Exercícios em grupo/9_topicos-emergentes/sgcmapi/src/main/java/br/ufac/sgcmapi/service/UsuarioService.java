package br.ufac.sgcmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.repository.UsuarioRepository;

@Service
public class UsuarioService implements IService<Usuario> {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public Page<Usuario> get(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public Usuario get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<Usuario> get(String termoBusca, Pageable page) {
        return repo.busca(termoBusca, page);
    }

    @Override
    public Usuario save(Usuario objeto) {
        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
        if (objeto.getSenha() == null || objeto.getSenha().isEmpty()) {
            Long id = objeto.getId();
            Usuario usuario = repo.findById(id).orElse(null);
            if (usuario != null) {
                objeto.setSenha(usuario.getSenha());
            }
        } else {
            objeto.setSenha(passEncoder.encode(objeto.getSenha()));
        }
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Usuario getByNomeUsuario(String nomeUsuario) {
        return repo.findByNomeUsuario(nomeUsuario);
    }
    
}
