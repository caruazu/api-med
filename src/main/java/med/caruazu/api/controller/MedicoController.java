package med.caruazu.api.controller;

import jakarta.validation.Valid;
import med.caruazu.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(
            @RequestBody
            @Valid
            MedicoDadosCadastro dados
        ){
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping
    public Page<MedicoDadosListagem> listar(
            @PageableDefault(size = 10,sort = {"nome"}            )
            Pageable paginacao
        ){
        return medicoRepository.findAll(paginacao).map(MedicoDadosListagem::new);
    }

    @PutMapping
    @Transactional
    public void editar(
            @RequestBody
            @Valid
            MedicoDadosAtualizar dados
    ){
        Medico medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizar(dados);
    }
}
