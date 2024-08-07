package med.caruazu.api.controller;

import jakarta.validation.Valid;
import med.caruazu.api.domain.usuario.AutenticacaoDados;
import med.caruazu.api.domain.usuario.Usuario;
import med.caruazu.api.infra.security.TokenService;
import med.caruazu.api.infra.security.tokenJWTDados;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDados autenticacaoDados) {
		var tokenAuthenticarion = new UsernamePasswordAuthenticationToken(autenticacaoDados.login(), autenticacaoDados.senha());
		var autenticacao = authenticationManager.authenticate(tokenAuthenticarion);
		var tokenJWT = tokenService.generateToken((Usuario) autenticacao.getPrincipal());

		return ResponseEntity.ok(new tokenJWTDados(tokenJWT));
	}
}
