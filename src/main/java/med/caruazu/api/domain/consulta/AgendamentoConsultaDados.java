package med.caruazu.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsultaDados(

		Long idMedico,

		@NotNull
		Long idPaciente,

		@NotNull
		@Future
		LocalDateTime data
) {
}
