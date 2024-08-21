package med.caruazu.api.controller;

import jakarta.validation.Valid;
import med.caruazu.api.domain.consulta.AgendaDeConsultasService;
import med.caruazu.api.domain.consulta.AgendamentoConsultaDados;
import med.caruazu.api.domain.consulta.ConsultaDadosDetalhamento;
import med.caruazu.api.domain.consulta.ConsultaDadosCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

	@Autowired
	private AgendaDeConsultasService agendaService;

	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDados dados){
		agendaService.agendar(dados);
		return ResponseEntity.ok(new ConsultaDadosDetalhamento(null,null,null,null));
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity cancelar(@RequestBody @Valid ConsultaDadosCancelamento dados){
		agendaService.cancelar(dados);
		return ResponseEntity.noContent().build();
	}
}
