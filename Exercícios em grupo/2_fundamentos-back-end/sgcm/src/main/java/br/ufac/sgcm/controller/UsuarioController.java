package br.ufac.sgcm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.dao.UsuarioDao;
import br.ufac.sgcm.model.TipoUsuario;
import br.ufac.sgcm.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UsuarioController implements IController<Usuario>{

    private UsuarioDao dao;

    public UsuarioController() {
        dao = new UsuarioDao();
    }

    public List<String> getTiposUsuarios() {
        return dao.getTipoUsuarios();
    }    

    @Override
    public List<Usuario> get() {
        return dao.get();
    }

    @Override
    public Usuario get(Long id) {
        return dao.get(id);
    }

    @Override
    public int save(Usuario objeto) {
        int registrosAfetados = 0;
        if (objeto.getId() != null) {
            registrosAfetados = dao.update(objeto);
        } else {
            registrosAfetados = dao.insert(objeto);
        }
        return registrosAfetados;
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    @Override
    public List<Usuario> processListRequest(HttpServletRequest request) {
        String paramExcluir = request.getParameter("excluir");
        if (paramExcluir != null && !paramExcluir.isEmpty()) {
            Long id = Long.parseLong(paramExcluir);
            this.delete(id);
        }

        List<Usuario> registros = new ArrayList<>();
        registros = this.get();
        return registros;
    }

    @Override
    public Usuario processFormRequest(HttpServletRequest request, HttpServletResponse response) {
        String submit = request.getParameter("submit");
        if (submit != null) {

            Usuario item = new Usuario();

            String paramId = request.getParameter("id");
            if (paramId != null && !paramId.isEmpty()) {
                Long id = Long.parseLong(paramId);
                item.setId(id);
            }

            item.setAtivo(Boolean.parseBoolean(request.getParameter("ativo")));
            item.setNome_completo(request.getParameter("nome_completo"));
            item.setNome_usuario(request.getParameter("nome_usuario"));
            item.setPapel(TipoUsuario.valueOf(request.getParameter("papel")));
            item.setSenha(request.getParameter("senha"));

            this.save(item);

            try {
                response.sendRedirect("Usuarios.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        Usuario item = new Usuario();
        String paramId = request.getParameter("id");
        if (paramId != null && !paramId.isEmpty()) {
            Long id = Long.parseLong(paramId);
            item = this.get(id);
        }

        return item;
    }
    
}
