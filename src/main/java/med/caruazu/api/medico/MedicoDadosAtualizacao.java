package med.caruazu.api.medico;

import jakarta.validation.constraints.NotNull;
import med.caruazu.api.endereco.EnderecoDados;

public record MedicoDadosAtualizacao(
		@NotNull
		Long id,
		String nome,
		String telefone,
		EnderecoDados endereco
) {
}
