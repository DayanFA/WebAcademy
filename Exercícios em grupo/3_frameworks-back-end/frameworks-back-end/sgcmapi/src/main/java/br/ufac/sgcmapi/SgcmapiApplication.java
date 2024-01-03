package br.ufac.sgcmapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

@SpringBootApplication
@Controller
public class SgcmapiApplication {

	@Autowired
	private AtendimentoRepository repo;

	// public SgcmapiApplication(AtendimentoRepository repo) {
	// 	this.repo = repo;
	// }

	@RequestMapping("/")
	@ResponseBody
	public String exemplo() {
		List<Atendimento> atendimentos = repo.findAll();
		StringBuilder resultado = new StringBuilder();
		resultado.append("<pre>");
		for (Atendimento item : atendimentos) {
			resultado.append(item.getId() + "\n");
			resultado.append(item.getData() + "\n");
			resultado.append(item.getHora() + "\n");
			resultado.append(item.getStatus() + "\n");
			resultado.append(item.getPaciente().getNome() + "\n");
			resultado.append(item.getProfissional().getNome() + "\n");
			if (item.getConvenio() != null) {
				resultado.append(item.getConvenio().getNome() + "\n");
			}
			resultado.append("\n");
		}
		resultado.append("</pre>");
		return resultado.toString();
		// return "Spring Boot!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SgcmapiApplication.class, args);
	}

}
