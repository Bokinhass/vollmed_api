package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.user.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("{api.security.token.secret}")
  private String secret;

  public TokenService() {
  }

  public String generateToken(Usuario usuario) {
    try {
      var algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
              .withIssuer("API Voll.med")
              .withSubject(usuario.getLogin())
              .withExpiresAt(dateExpire())
              .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error generate token", exception);
    }
  }

  public String getSubject(String tokenJWT) {
    try {
      var algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
              .withIssuer("API Voll.med")
              .build()
              .verify(tokenJWT)
              .getSubject();

    } catch (JWTVerificationException exception) {
      throw new RuntimeException("Invalid Token JWT");
    }
  }

  private Instant dateExpire() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
