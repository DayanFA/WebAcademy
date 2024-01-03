package br.ufac.sgcm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import br.ufac.sgcm.controller.ProfissionalController;
import br.ufac.sgcm.model.Profissional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importações necessárias

@WebServlet("/profissional")
public class BuscaProfissionalServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Security-Policy", "default-src 'self' http://localhost:8080");
        String termoBusca = request.getParameter("busca");
        //String resultados = executarBusca(termoBusca);

        // Converter os resultados para JSON
        //ObjectMapper objectMapper = new ObjectMapper();
        //String jsonResultados = objectMapper.writeValueAsString(resultados);

        // Configurar a resposta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Enviar a resposta JSON
        PrintWriter out = response.getWriter();
        //out.print(jsonResultados);
        
        out.print(executarBusca(termoBusca));
        out.flush();
    }

    ProfissionalController profissionalController = new ProfissionalController();

    
    public String executarBusca(String termoBusca) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Profissional> registros = profissionalController.getBusca(termoBusca);

        try {
            String json = mapper.writeValueAsString(registros);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }  
    }
}
