package med.caruazu.api.controller;

import jakarta.validation.Valid;
import med.caruazu.api.medico.Medico;
import med.caruazu.api.medico.MedicoDadosCadastro;
import med.caruazu.api.medico.MedicoRepository;
import med.caruazu.api.medico.MedicoDadosListagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<MedicoDadosListagem> listar(){
        return medicoRepository.findAll()
                .stream()
                .map(MedicoDadosListagem::new)
                .toList();
    }
}
