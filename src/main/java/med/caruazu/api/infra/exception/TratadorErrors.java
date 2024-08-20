package med.caruazu.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrors {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity TratadorErros404(){
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity TratadorErrors400(MethodArgumentNotValidException e){
		var erroPersonalizado = e.getFieldErrors().stream().map(ErroValidacaoDados::new).toList();
		return ResponseEntity.badRequest().body(erroPersonalizado);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity TratadorErros401BadCredentials(){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity TratadorErros401Autenticação() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity TratadorErros403() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity TratadorErros500(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
	}

	private record ErroValidacaoDados(String campo, String mensagem){
		public ErroValidacaoDados(FieldError e){
			this(e.getField(), e.getDefaultMessage());
		}
	}


}
