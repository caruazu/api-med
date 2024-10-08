package med.caruazu.api.controller;


import jakarta.validation.Valid;
import med.caruazu.api.domain.paciente.PacienteDadosCadastro;
import med.caruazu.api.domain.paciente.PacienteRepository;
import med.caruazu.api.domain.paciente.PacienteDadosDetalhamento;
import med.caruazu.api.domain.paciente.PacienteDadosListagem;
import med.caruazu.api.domain.paciente.PacienteDadosAtualizacao;
import med.caruazu.api.domain.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid PacienteDadosCadastro dados, UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(dados);
		repository.save(paciente);

		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new PacienteDadosDetalhamento(paciente));
	}

	@GetMapping
	public ResponseEntity<Page<PacienteDadosListagem>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(PacienteDadosListagem::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid PacienteDadosAtualizacao dados) {
		var paciente = repository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);

		return ResponseEntity.ok(new PacienteDadosDetalhamento(paciente));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		paciente.excluir();

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		return ResponseEntity.ok(new PacienteDadosDetalhamento(paciente));
	}


}