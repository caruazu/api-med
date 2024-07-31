package med.caruazu.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.caruazu.api.domain.endereco.EnderecoDados;

public record MedicoDadosAtualizacao(
		@NotNull
		Long id,
		String nome,
		String telefone,
		EnderecoDados endereco
) {
}
