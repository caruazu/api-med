package med.caruazu.api.medico;

import jakarta.validation.constraints.NotNull;
import med.caruazu.api.endereco.EnderecoDados;

public record MedicoDadosAtualizar(
		@NotNull
		Long id,
		String nome,
		String telefone,
		EnderecoDados endereco
) {
}
