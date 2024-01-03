package br.ufac.sgcm.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.dao.ProfissionalDao;
import br.ufac.sgcm.model.Profissional;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProfissionalController implements IController<Profissional> {

    private ProfissionalDao dao;

    public ProfissionalController() {
        dao = new ProfissionalDao();
    }

    @Override
    public List<Profissional> get() {
        return dao.get();
    }

    @Override
    public Profissional get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public int save(Profissional objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    @Override
    public List<Profissional> processListRequest(HttpServletRequest request) {

        String paramExcluir = request.getParameter("excluir");
        if (paramExcluir != null && !paramExcluir.isEmpty()) {
            Long id = Long.parseLong(paramExcluir);
            this.delete(id);
        }

        List<Profissional> registros = new ArrayList<>();
        registros = this.get();
        return registros;

    }

    @Override
    public Profissional processFormRequest(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processFormRequest'");
    }
    
}
