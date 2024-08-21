package med.caruazu.api.domain.consulta.validacoes;

import med.caruazu.api.domain.ValidacaoException;
import med.caruazu.api.domain.consulta.AgendamentoConsultaDados;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {
	public void validar(AgendamentoConsultaDados dados){
		var dataConsulta = dados.data();

		var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var antesDaAberturaClinica = dataConsulta.getHour() < 7;
		var depoisDoEncerramentoClinica = dataConsulta.getHour() > 18;

		if (domingo || antesDaAberturaClinica || depoisDoEncerramentoClinica) {
			throw new ValidacaoException("Consulta fora do horario de funcionamento da clinica");
		}
	}
}
