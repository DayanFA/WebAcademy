package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Especialidade;

public class EspecialidadeDao implements IDao<Especialidade> {

    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public EspecialidadeDao() {
        conexao = ConexaoDB.getConexao();
    }

    @Override
    public List<Especialidade> get() {
        List<Especialidade> registros = new ArrayList<>();
        String sql = "SELECT * from especialidade";
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Especialidade registro = new Especialidade();
                registro.setId(rs.getLong("id"));
                registro.setNome(rs.getString("nome"));
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public Especialidade get(Long id) {
        Especialidade registro = new Especialidade();
        String sql = "SELECT * FROM especialidade WHERE id=?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                registro.setId(rs.getLong("id"));
                registro.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registro;
    }

    @Override
    public List<Especialidade> get(String termoBusca) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public int insert(Especialidade objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(Especialidade objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
