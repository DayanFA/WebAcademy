package br.ufac.sgcmapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.sgcmapi.config.PerfilUsuario;
import br.ufac.sgcmapi.config.TokenService;
import br.ufac.sgcmapi.model.TokenResponse;
import br.ufac.sgcmapi.model.Usuario;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody Usuario usuario) {

        UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(usuario.getNomeUsuario(), usuario.getSenha());
        Authentication auth = authManager.authenticate(loginToken);
        PerfilUsuario principal = (PerfilUsuario) auth.getPrincipal();
        Usuario usuarioAutenticado = usuarioService.getByNomeUsuario(principal.getUsername());

        String token = tokenService.generateToken(usuarioAutenticado);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);

        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);

    }
    
}
