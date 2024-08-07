package med.caruazu.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.caruazu.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String generateToken(Usuario usuario) {
		try {
			Algorithm algoritimo = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("api-med")
					.withSubject(usuario.getLogin())
					.withExpiresAt(dataExpiracao())
					.sign(algoritimo);
		} catch (JWTCreationException exception){
			throw new RuntimeException("erro ao gerar o token JWT", exception);
		}
	}

	public String decodeToken(String tokenJWT) {
		try {
			Algorithm algoritimo = Algorithm.HMAC256(secret);
			return JWT.require(algoritimo)
					// specify any specific claim validations
					.withIssuer("api-med")
					// reusable verifier instance
					.build()
					.verify(tokenJWT)
					.getSubject();
		} catch (JWTVerificationException exception){
			throw new RuntimeException("token inválido", exception);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
