package br.ufac.sgcmapi.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.ufac.sgcmapi.model.Usuario;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        Algorithm alg = Algorithm.HMAC256(secret);
        String token = JWT.create()
                          .withIssuer("SGCM")
                          .withSubject(usuario.getNomeUsuario())
                          .withClaim("nomeCompleto", usuario.getNomeCompleto())
                          .withClaim("papel", usuario.getPapel().toString())
                          .withExpiresAt(generateExpirationDate())
                          .sign(alg);
        return token;
    }

    public String validateToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        return JWT.require(alg)
                  .withIssuer("SGCM")
                  .build()
                  .verify(token)
                  .getSubject();
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusDays(1)
                            .toInstant(ZoneOffset.of("-05:00"));
    }
    
}
