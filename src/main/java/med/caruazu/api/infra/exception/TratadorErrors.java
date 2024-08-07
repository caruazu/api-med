package med.caruazu.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrors {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity TratadorErrors404(){
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity TratadorErrors400(MethodArgumentNotValidException e){
		var erroPersonalizado = e.getFieldErrors().stream().map(ErroValidacaoDados::new).toList();
		return ResponseEntity.badRequest().body(erroPersonalizado);
	}

	private record ErroValidacaoDados(String campo, String mensagem){
		public ErroValidacaoDados(FieldError e){
			this(e.getField(), e.getDefaultMessage());
		}
	}
}
