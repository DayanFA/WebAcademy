package br.ufac.sgcmapi.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.service.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController implements IController<Atendimento> {

    @Autowired
    private AtendimentoService servico;

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<Atendimento>> get(Pageable page) {
        Page<Atendimento> registros = servico.get(page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Atendimento> get(@PathVariable Long id) {
        Atendimento registro = servico.get(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @GetMapping("/busca/{termoBusca}")
    public ResponseEntity<Page<Atendimento>> get(@PathVariable("termoBusca") String termoBusca, Pageable page) {
        Page<Atendimento> registros = servico.get(termoBusca, page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/lista-agendamentos/")
    public ResponseEntity<Page<Atendimento>> getAgendamentos(Pageable page) {
        List<EStatus> status = Arrays.asList(EStatus.AGENDADO, EStatus.CONFIRMADO);
        Page<Atendimento> registros = servico.get(status, page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/lista-agendamentos/busca/{termoBusca}")
    public ResponseEntity<Page<Atendimento>> getAgendamentos(@PathVariable("termoBusca") String termoBusca, Pageable page) {
        List<EStatus> status = Arrays.asList(EStatus.AGENDADO, EStatus.CONFIRMADO);
        Page<Atendimento> registros = servico.get(termoBusca, status, page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/lista-atendimentos/")
    public ResponseEntity<Page<Atendimento>> getAtendimentos(Pageable page) {
        List<EStatus> status = Arrays.asList(EStatus.CHEGADA, EStatus.ATENDIMENTO);
        Page<Atendimento> registros = servico.get(status, page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/lista-atendimentos/busca/{termoBusca}")
    public ResponseEntity<Page<Atendimento>> getAtendimentos(@PathVariable("termoBusca") String termoBusca, Pageable page) {
        List<EStatus> status = Arrays.asList(EStatus.CHEGADA, EStatus.ATENDIMENTO);
        Page<Atendimento> registros = servico.get(termoBusca, status, page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Atendimento> insert(@RequestBody Atendimento objeto) {
        Atendimento registro = servico.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Atendimento> update(@RequestBody Atendimento objeto) {
        Atendimento registro = servico.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Atendimento> updateStatus(@PathVariable("id") Long id) {
        Atendimento registro = servico.updateStatus(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @GetMapping("/horarios/{idProfissional}/{data}")
    public ResponseEntity<List<String>> getHorarios(
            @PathVariable("idProfissional") Long idProfissional,
            @PathVariable("data") LocalDate data) {
        List<String> horarios = servico.getHorarios(idProfissional, data);
        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }
    
}
