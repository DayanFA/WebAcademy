package br.ufac.sgcmapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/config/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.get();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.get(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        Usuario atualizadoUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(atualizadoUsuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/papel/{papel}")
    public ResponseEntity<List<Usuario>> getUsuariosByPapel(@PathVariable String papel) {
        List<Usuario> usuarios = usuarioService.getByPapel(papel);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/nomecompleto/{nomeCompleto}")
    public ResponseEntity<List<Usuario>> getUsuariosByNomeCompleto(@PathVariable String nomeCompleto) {
        List<Usuario> usuarios = usuarioService.getByNomeCompleto(nomeCompleto);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/nomeusuario/{nomeUsuario}")
    public ResponseEntity<List<Usuario>> getUsuariosByNomeUsuario(@PathVariable String nomeUsuario) {
        List<Usuario> usuarios = usuarioService.get(nomeUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/ativos/{ativo}")
    public ResponseEntity<List<Usuario>> getUsuariosAtivos(@PathVariable boolean ativo) {
        List<Usuario> usuarios = usuarioService.getAtivos(ativo);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
