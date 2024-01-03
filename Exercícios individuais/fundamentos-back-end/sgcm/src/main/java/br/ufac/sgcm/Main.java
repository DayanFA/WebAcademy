package br.ufac.sgcm;

import java.sql.Connection;
import java.util.List;

import br.ufac.sgcm.dao.ConexaoDB;
import br.ufac.sgcm.dao.ProfissionalDao;
import br.ufac.sgcm.model.Especialidade;
import br.ufac.sgcm.model.Profissional;
import br.ufac.sgcm.model.Unidade;

public class Main {

    public static void main(String[] args) {

        Unidade unidade = new Unidade();
        unidade.setId(1L);
        unidade.setNome("Unidade A");

        Especialidade especialidade = new Especialidade();
        especialidade.setId(1L);
        especialidade.setNome("Cardiologia");

        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setNome("Daniel");
        profissional.setEspecialidade(especialidade);
        profissional.setUnidade(unidade);

        System.out.println(profissional.getNome());
        System.out.println(profissional.getEspecialidade().getNome());
        System.out.println(profissional.getUnidade().getNome());

        Connection conexao = ConexaoDB.getConexao();
        if (conexao != null) {
            System.out.println("Conexão realizado com sucesso!");
        } else {
            System.out.println("Não deu...");
        }

        ProfissionalDao dao = new ProfissionalDao();

        profissional.setTelefone("(68) 5555-5555");
        profissional.setEmail("daniel@ufac.br");
        profissional.setRegistroConselho("CRM/AC 555");
        // dao.insert(profissional);

        profissional = dao.get(7L);
        profissional.setNome("Daniel Silva");
        dao.update(profissional);

        // dao.delete(2L);

        List<Profissional> profissionais = dao.get("cardio");
        for (Profissional item : profissionais) {
            System.out.println(item.getNome());
            System.out.println(item.getEspecialidade().getNome());
            System.out.println(item.getUnidade().getNome());
            System.out.println();
        }
        
    }
    
}
