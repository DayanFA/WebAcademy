package br.ufac.sgcm;

import java.io.IOException;
import java.io.PrintWriter;

import br.ufac.sgcm.dao.ProfissionalDao;
import br.ufac.sgcm.model.Profissional;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

public class PrimeiroServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) {

        ProfissionalDao dao = new ProfissionalDao();
        Profissional profissional = dao.get(1L);

        String nome = req.getParameter("nome");

        try {
            PrintWriter saida = res.getWriter();
            saida.println("<html>");
            saida.println("<head>");
            saida.println("<title>Primeiro Servlet</title>");
            saida.println("</head>");
            saida.println("<body>");
            saida.println("<h1>Nome do profisisonal: " + profissional.getNome() + "</h1>");
            saida.println("<h2>Nome do paciente: " + nome + "</h2");
            saida.println("</body>");
            saida.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
