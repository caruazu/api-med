package med.caruazu.api.controller;

import jakarta.validation.Valid;
import med.caruazu.api.domain.usuario.AutenticacaoDados;
import med.caruazu.api.domain.usuario.Usuario;
import med.caruazu.api.domain.usuario.UsuarioRepository;
import med.caruazu.api.infra.security.TokenService;
import med.caruazu.api.infra.security.tokenJWTDados;
import med.caruazu.api.domain.usuario.AutenticacaoDadosCadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDados autenticacaoDados) {
		var tokenAuthenticarion = new UsernamePasswordAuthenticationToken(autenticacaoDados.login(), autenticacaoDados.senha());
		var autenticacao = authenticationManager.authenticate(tokenAuthenticarion);
		var tokenJWT = tokenService.generateToken((Usuario) autenticacao.getPrincipal());

		return ResponseEntity.ok(new tokenJWTDados(tokenJWT));
	}

	@PostMapping("/cadastro")
	public ResponseEntity efetuarCadastro(@RequestBody @Valid AutenticacaoDadosCadastro autenticacaoDadosCadastro) {
		String login = autenticacaoDadosCadastro.login();
		String senha = autenticacaoDadosCadastro.senha();

		if(usuarioRepository.findAllByLogin(login) == null){
			String senhaCriptografada = passwordEncoder.encode(senha);
			Usuario usuarioNovo = new Usuario(null, login, senhaCriptografada);
			usuarioRepository.save(usuarioNovo);
			return ResponseEntity.ok("Cadastro efetuado com sucesso");
		}else{
			return ResponseEntity.badRequest().body("Usuário já cadastrado");
		}
	}
}
