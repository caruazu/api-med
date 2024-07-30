package med.caruazu.api.controller;

import jakarta.validation.Valid;
import med.caruazu.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody @Valid MedicoDadosCadastro dados,
            UriComponentsBuilder uriComponentsBuilder
    ){
        Medico medico = new Medico(dados);
        medicoRepository.save(medico);

        var medico_id = medico.getId();
        var uri = uriComponentsBuilder.path("/medicos/{id}")
                .buildAndExpand(medico_id)
                .toUri();
        var dto = new MedicoDadosDetalhamento(medico);

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDadosListagem>> listar(
            @PageableDefault(size = 10,sort = {"nome"}            )
            Pageable paginacao
        ){
         Page page = medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoDadosListagem::new);
         return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid MedicoDadosAtualizacao dados){
        Medico medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizar(dados);
        return ResponseEntity.ok(new MedicoDadosDetalhamento(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        System.out.println(medico.isAtivo());
        medico.excluir();
        System.out.println(medico.isAtivo());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDadosDetalhamento(medico));
    }
}
