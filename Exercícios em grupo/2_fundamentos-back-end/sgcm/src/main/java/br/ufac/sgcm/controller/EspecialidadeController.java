package br.ufac.sgcm.controller;

import java.util.List;

import br.ufac.sgcm.dao.EspecialidadeDao;
import br.ufac.sgcm.model.Especialidade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EspecialidadeController implements IController<Especialidade> {

    private EspecialidadeDao dao;

    public EspecialidadeController() {
        dao = new EspecialidadeDao();
    }

    @Override
    public List<Especialidade> get() {
        return dao.get();
    }

    @Override
    public Especialidade get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public int save(Especialidade objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public int delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Especialidade> processListRequest(HttpServletRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processListRequest'");
    }

    @Override
    public Especialidade processFormRequest(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processFormRequest'");
    }
    
}
