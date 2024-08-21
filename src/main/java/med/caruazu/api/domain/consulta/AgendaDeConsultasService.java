package med.caruazu.api.domain.consulta;

import med.caruazu.api.domain.ValidacaoException;
import med.caruazu.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.caruazu.api.domain.medico.Medico;
import med.caruazu.api.domain.medico.MedicoRepository;
import med.caruazu.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultasService {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private List<ValidadorAgendamentoDeConsulta> validadores;

	public void agendar(AgendamentoConsultaDados dados){

		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id informado para o paciente não existe");
		}

		if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id informado para o médico não existe");
		}

		validadores.forEach(validador -> validador.validar(dados));

		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		var medico = escolherMedico(dados);
		var consulta = new Consulta(null, medico, paciente, dados.data(), null);

		// salvar no banco
		System.out.println(consulta);
	}

	private Medico escolherMedico(AgendamentoConsultaDados dados) {
		if(dados.idMedico() != null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}

		if(dados.especialidade() == null){
			throw new ValidacaoException("Quando não for escolhido um médico, é necessário especificar uma especialidade");
		}

		return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
	}

	public void cancelar(ConsultaDadosCancelamento dados) {
		if(!consultaRepository.existsById(dados.idConsulta())){
			throw new ValidacaoException("Id informado para a consulta não existe");
		}
		var consulta = consultaRepository.getReferenceById(dados.idConsulta());
		consulta.cancelar(dados.motivo());
	}
}
