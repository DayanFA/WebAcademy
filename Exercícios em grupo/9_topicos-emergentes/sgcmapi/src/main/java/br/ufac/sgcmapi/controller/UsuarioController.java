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

import br.ufac.sgcmapi.controller.dto.UsuarioDto;
import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.service.UsuarioService;


@RestController
@RequestMapping("/config/usuario")
public class UsuarioController implements IController<Usuario> {

    @Autowired
    private UsuarioService servico;

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<UsuarioDto>> get(Pageable page) {
        Page<Usuario> registros = servico.get(page);
        Page<UsuarioDto> listDto = registros.map(UsuarioDto::fromEntity);
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> get(@PathVariable("id") Long id) {
        Usuario registro = servico.get(id);
        UsuarioDto dto = UsuarioDto.fromEntity(registro);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    @GetMapping("/busca/{termoBusca}")
    public ResponseEntity<Page<UsuarioDto>> get(@PathVariable("termoBusca") String termoBusca, Pageable page) {
        Page<Usuario> registros = servico.get(termoBusca, page);
        Page<UsuarioDto> listDto = registros.map(UsuarioDto::fromEntity);
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Usuario> insert(@RequestBody Usuario objeto) {
        Usuario registro = servico.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Usuario> update(@RequestBody Usuario objeto) {
        Usuario registro = servico.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
