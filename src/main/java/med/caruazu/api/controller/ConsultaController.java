package med.caruazu.api.controller;

import jakarta.validation.Valid;
import med.caruazu.api.domain.consulta.AgendamentoConsultaDados;
import med.caruazu.api.domain.consulta.ConsultaDadosDetalhamento;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

	@PostMapping
	@Transactional
	public ResponseEntity agendar(
			@RequestBody
			@Valid
			AgendamentoConsultaDados dados
	){
		System.out.println(dados);
		return ResponseEntity.ok(new ConsultaDadosDetalhamento(null,null,null,null));
	}
}
