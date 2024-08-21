package med.caruazu.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import med.caruazu.api.domain.motivoCancelamento.MotivoCancelamento;

public record ConsultaDadosCancelamento(
		@NotNull
		Long idConsulta,

		@NotNull
		MotivoCancelamento motivo
) {
}
