package med.caruazu.api.controller;

import jakarta.validation.Valid;
import med.caruazu.api.medico.MedicoDados;
import med.caruazu.api.medico.Medico;
import med.caruazu.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            MedicoDados dados
        ){
        medicoRepository.save(new Medico(dados));
    }
}
