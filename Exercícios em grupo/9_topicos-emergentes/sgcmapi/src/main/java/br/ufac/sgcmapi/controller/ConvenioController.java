package br.ufac.sgcmapi.controller;

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

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.service.ConvenioService;

@RestController
@RequestMapping("/convenio")
public class ConvenioController implements IController<Convenio> {

    @Autowired
    private ConvenioService servico;

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<Convenio>> get(Pageable page) {
        Page<Convenio> registros = servico.get(page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Convenio>> get() {
        Page<Convenio> registros = servico.get(Pageable.unpaged());
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Convenio> get(@PathVariable("id") Long id) {
        Convenio registro = servico.get(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @GetMapping("/busca/{termoBusca}")
    public ResponseEntity<Page<Convenio>> get(@PathVariable("termoBusca") String termoBusca, Pageable page) {
        Page<Convenio> registros = servico.get(termoBusca, page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Convenio> insert(@RequestBody Convenio objeto) {
        Convenio registro = servico.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Convenio> update(@RequestBody Convenio objeto) {
        Convenio registro = servico.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
