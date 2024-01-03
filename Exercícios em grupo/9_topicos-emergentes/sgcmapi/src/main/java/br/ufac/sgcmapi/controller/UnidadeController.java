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

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.service.UnidadeService;

@RestController
@RequestMapping("/config/unidade")
public class UnidadeController implements IController<Unidade> {

    @Autowired
    private UnidadeService servico;

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<Unidade>> get(Pageable page) {
        Page<Unidade> registros = servico.get(page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Unidade>> get() {
        Page<Unidade> registros = servico.get(Pageable.unpaged());
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Unidade> get(@PathVariable("id") Long id) {
        Unidade registro = servico.get(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @GetMapping("/busca/{termoBusca}")
    public ResponseEntity<Page<Unidade>> get(@PathVariable("termoBusca") String termoBusca, Pageable page) {
        Page<Unidade> registros = servico.get(termoBusca, page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Unidade> insert(@RequestBody Unidade objeto) {
        Unidade registro = servico.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Unidade> update(@RequestBody Unidade objeto) {
        Unidade registro = servico.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
