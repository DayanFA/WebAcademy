package br.ufac.sgcm.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Usuario;
import br.ufac.sgcm.model.TipoUsuario;

public class UsuarioDao implements IDao<Usuario>{

    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public UsuarioDao () {
        conexao = ConexaoDB.getConexao();
    }

    public List<String> getTipoUsuarios() {
        List<String> papeis = new ArrayList<>();
        String sql = "SELECT papel FROM usuario";
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                String papel = rs.getString("papel");
                papeis.add(papel);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return papeis;
    }

    @Override
    public List<Usuario> get() {
        List<Usuario> registros = new ArrayList<>();
        String sql = "SELECT * FROM sgcm.usuario";
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario registro = new Usuario();
                registro.setId(rs.getLong("id"));
                registro.setAtivo(rs.getBoolean("ativo"));
                registro.setNome_completo(rs.getString("nome_completo"));
                registro.setNome_usuario(rs.getString("nome_usuario"));
                registro.setPapel(TipoUsuario.valueOf(rs.getString("papel").toUpperCase()));
                registro.setSenha(rs.getString("senha"));
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public Usuario get(Long id) {
        Usuario registro = new Usuario();
        String sql = "SELECT * FROM usuario WHERE id=?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                registro.setId(rs.getLong("id"));
                registro.setAtivo(rs.getBoolean("ativo"));
                registro.setNome_completo(rs.getString("nome_completo"));
                registro.setNome_usuario(rs.getString("nome_usuario"));
                registro.setPapel(TipoUsuario.valueOf(rs.getString("papel").toUpperCase()));
                registro.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registro;
    }

    @Override
    public List<Usuario> get(String termoBusca) {
        List<Usuario> registros = new ArrayList<>();
        String sql = "SELECT u.*, u.nome_completo, u.nome_usuario, u.papel FROM usuario AS u" +
            " WHERE u.nome_completo LIKE ?" +
            " OR u.nome_usuario LIKE ?" +
            " OR u.papel LIKE ?" +
            " OR u.senha LIKE ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + termoBusca + "%");
            ps.setString(2, "%" + termoBusca + "%");
            ps.setString(3, "%" + termoBusca + "%");
            ps.setString(4, "%" + termoBusca + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario registro = new Usuario();
                registro.setId(rs.getLong("id"));
                registro.isAtivo();
                registro.setNome_completo(rs.getString("nome_completo"));
                registro.setNome_usuario(rs.getString("nome_usuario"));
                registro.setPapel(TipoUsuario.valueOf(rs.getString("papel").toUpperCase()));
                registro.setSenha(rs.getString("senha"));
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public int insert(Usuario objeto) {
        int registroAfetados = 0;
        String sql = "INSERT INTO sgcm.usuario" +
            " (ativo, nome_completo, nome_usuario, papel, senha)" +
            " VALUES (?, ?, ?, ?, ?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setBoolean(1, objeto.isAtivo());
            ps.setString(2, objeto.getNome_completo());
            ps.setString(3, objeto.getNome_usuario());
            ps.setString(4, objeto.getPapel().name());
            ps.setString(5, objeto.getSenha());
            registroAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registroAfetados;
    }

    @Override
    public int update(Usuario objeto) {
        int registroAfetados = 0;
        String sql = "UPDATE sgcm.usuario SET" +
            " ativo=?," +
            " nome_completo=?," +
            " nome_usuario=?," +
            " papel=?," +
            " senha=?," +
            " WHERE id=?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setBoolean(1, objeto.isAtivo());
            ps.setString(2, objeto.getNome_completo());
            ps.setString(3, objeto.getNome_usuario());
            ps.setString(4, objeto.getPapel().name());
            ps.setString(5, objeto.getSenha());
            ps.setLong(6, objeto.getId());
            registroAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registroAfetados;
    }

    @Override
    public int delete(Long id) {
        int registroAfetados = 0;
        String sql = "DELETE FROM usuario WHERE id=?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            registroAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registroAfetados;
    
    }
    
}
