package med.caruazu.api.domain.consulta;

import java.time.LocalDateTime;

public record ConsultaDadosDetalhamento(
		Long id,
		Long idMedico,
		Long idPaciente,
		LocalDateTime data
) {
}
