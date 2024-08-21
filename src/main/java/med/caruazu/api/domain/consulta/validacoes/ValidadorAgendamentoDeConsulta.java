package med.caruazu.api.domain.consulta.validacoes;

import med.caruazu.api.domain.consulta.AgendamentoConsultaDados;
import med.caruazu.api.domain.consulta.Consulta;

public interface ValidadorAgendamentoDeConsulta {

	void validar(AgendamentoConsultaDados dados);
}
