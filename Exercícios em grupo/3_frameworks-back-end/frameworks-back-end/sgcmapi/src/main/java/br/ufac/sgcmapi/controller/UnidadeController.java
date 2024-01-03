package br.ufac.sgcmapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.service.UnidadeService;

import java.util.List;

@RestController
@RequestMapping("/config/unidade")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @GetMapping("/")
    public ResponseEntity<List<Unidade>> getAllUnidades() {
        List<Unidade> unidades = unidadeService.get();
        return new ResponseEntity<>(unidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> getUnidadeById(@PathVariable Long id) {
        Unidade unidade = unidadeService.get(id);
        if (unidade != null) {
            return new ResponseEntity<>(unidade, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Unidade> createUnidade(@RequestBody Unidade unidade) {
        Unidade novaUnidade = unidadeService.save(unidade);
        return new ResponseEntity<>(novaUnidade, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Unidade> updateUnidade(@RequestBody Unidade unidade) {
        Unidade unidadeAtualizada = unidadeService.save(unidade);
        return new ResponseEntity<>(unidadeAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUnidade(@PathVariable Long id) {
        unidadeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/endereco/{termoBusca}")
    public ResponseEntity<List<Unidade>> getUnidadesByEndereco(@PathVariable String termoBusca) {
        List<Unidade> unidades = unidadeService.get(termoBusca);
        return new ResponseEntity<>(unidades, HttpStatus.OK);
    }

    @GetMapping("/nome/{nomeCompleto}")
    public ResponseEntity<List<Unidade>> getUnidadesByNome(@PathVariable String nomeCompleto) {
        List<Unidade> unidades = unidadeService.getByNomeCompleto(nomeCompleto);
        return new ResponseEntity<>(unidades, HttpStatus.OK);
    }
}
