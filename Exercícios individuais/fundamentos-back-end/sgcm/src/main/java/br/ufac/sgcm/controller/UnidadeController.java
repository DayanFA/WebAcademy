package br.ufac.sgcm.controller;

import java.util.List;

import br.ufac.sgcm.dao.UnidadeDao;
import br.ufac.sgcm.model.Unidade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UnidadeController implements IController<Unidade> {

    private UnidadeDao dao;

    public UnidadeController() {
        dao = new UnidadeDao();
    }

    @Override
    public List<Unidade> get() {
        return dao.get();
    }

    @Override
    public Unidade get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public int save(Unidade objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public int delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Unidade> processListRequest(HttpServletRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processListRequest'");
    }

    @Override
    public Unidade processFormRequest(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processFormRequest'");
    }
    
}
