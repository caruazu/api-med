package med.caruazu.api.domain.consulta.validacoes;

import med.caruazu.api.domain.ValidacaoException;
import med.caruazu.api.domain.consulta.AgendamentoConsultaDados;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{
	public void validar(AgendamentoConsultaDados dados){
		var dataConsulta = dados.data();
		var agora = LocalTime.now();
		var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

		if(diferencaEmMinutos < 30){
			throw new ValidacaoException("Consulta deve ser agendada com antecedencia mínima de 30 minutos");
		}
	}
}
